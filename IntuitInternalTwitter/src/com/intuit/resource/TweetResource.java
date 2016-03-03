package com.intuit.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.intuit.jaxb.model.Tweet;
import com.intuit.service.TweetService;
import com.intuit.service.TweetServiceImpl;

@Path("/")
public class TweetResource {
	
	  TweetService tweetService = new TweetServiceImpl();
	  
	  @Path("/tweet")
	  @POST
	  @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
	  @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
	  public Response addTweet(Tweet tweet, @Context UriInfo uriInfo){
	   Tweet newTweet =  tweetService.addTweet(tweet);
	   String newId = String.valueOf(newTweet.getTweetId());
	   URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newTweet)
				.build();
	  }
	  
	  @Path("/feed")
	  @GET
	  @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Tweet> getFeeds(@QueryParam(value = "u") Long userId) {
	     return tweetService.getAllTweets(userId);
	     /*System.out.println(Response.ok()
					.entity(tweets)
					.build());
	     return Response.ok()
					.entity(tweets)
					.build();*/
	  }


}
