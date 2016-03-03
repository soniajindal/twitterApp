package com.intuit.client;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.intuit.exception.DataNotFoundException;
import com.intuit.jaxb.model.Tweet;
import com.intuit.messagereader.TweetMessageReader;

public class TweetResourceTest {
	Client client = ClientBuilder.newClient();
	WebTarget baseTarget = client.target("http://localhost:8080/IntuitInternalTwitter/intuitapi/");

	@Test
	public void testTweetService() {
		WebTarget tweetTarget = baseTarget.path("tweet");
		Tweet newMessage = new Tweet(4L, "My New message from JAX-RS client", 1L);
		Response postResponse = tweetTarget
			.request()
			.post(Entity.json(newMessage));
		if (postResponse.getStatus() != 201) {
			System.out.println("Error");
		}
		assertEquals(postResponse.getStatus(), 201);
		Tweet createdMessage = postResponse.readEntity(Tweet.class);
		assertEquals(createdMessage.getStatus(), "My New message from JAX-RS client");
	
		
	}
	
	@Test
	public void testFeedService() {
		WebTarget feedTarget = baseTarget.path("feed");
		List<Tweet> feeds = feedTarget
				.queryParam("u", 1L)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Tweet>>() { });
		assertTrue(feeds.size() > 0);
		assertTrue(feeds.get(0).getStatus().equals("My first Tweet"));
		//Tweet newMessage = new Tweet(4L, "My New message from JAX-RS client", 1L);
		//assertTrue(feeds.contains(newMessage)); -- override equals for this
	}
	
	@Test
	public void testFeedService1() {
		WebTarget feedTarget = baseTarget.path("feed");
		List<Tweet> feeds = feedTarget
				.queryParam("u", 2L)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Tweet>>() { });
		assertTrue(feeds.size() > 0);
		//assertTrue(feeds.get(i).getStatus().equals("My New message from JAX-RS client"));
		//Tweet newMessage = new Tweet(4L, "My New message from JAX-RS client", 1L);
		//assertTrue(feeds.contains(newMessage)); -- override equals for this

	}
	
	@Test(expected=NotFoundException.class)
	public void testFeedException() { 
		WebTarget feedTarget = baseTarget.path("feed");
		List<Tweet> feeds = feedTarget
				.queryParam("u", 3L)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Tweet>>() { });		


		
	}
}

