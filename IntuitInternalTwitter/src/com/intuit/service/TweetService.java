package com.intuit.service;

import java.util.List;
import com.intuit.jaxb.model.Tweet;

public interface TweetService {
	
	
	public List<Tweet> getAllTweets(Long userId);

	public Tweet addTweet(Tweet tweet) ;
		
	
}
