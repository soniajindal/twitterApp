package com.intuit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.intuit.cache.MetadataServiceCacheManager;
import com.intuit.exception.DataNotFoundException;
import com.intuit.jaxb.model.Tweet;

public class TweetServiceImpl implements TweetService{
	

	public List<Tweet> getAllTweets(Long userId) {
		//if cache is empty for any reason please reload
		if(MetadataServiceCacheManager.getInstance().getUserFollows().isEmpty()) {
			MetadataServiceCacheManager.getInstance().loadAllCache(); 
		}
			Set<Long> followsSet = MetadataServiceCacheManager.getInstance().getUserFollows().get(userId);

			List<Tweet> tweets = new ArrayList<Tweet>();
			if(followsSet == null) {
				throw new DataNotFoundException("No tweets found for userid" + userId);

			}
			for(Long userFollowsId: followsSet) {
				tweets.addAll(MetadataServiceCacheManager.getInstance().getUserTweet().get(userFollowsId));
			}
			if(tweets.isEmpty()) {
				throw new DataNotFoundException("No tweets found for userid" + userId);

			}
			return tweets;
		
	}

	public Tweet addTweet(Tweet tweet) {
		//TBD do validations like if status is duplicate in a timeperiod for a userId
		//TBD throw error if these validations don't pass
		if(MetadataServiceCacheManager.getInstance().getUserFollowerIds().isEmpty()) {
			MetadataServiceCacheManager.getInstance().loadAllCache();  //if cache is empty for any reason please reload
		}
		System.out.println(MetadataServiceCacheManager.getInstance().getUserFollowerIds());
		Set<Long> followers = MetadataServiceCacheManager.getInstance().getUserFollowerIds().get(tweet.getUserId());
		for(Long followerId: followers) {
			//send notification to each of them
		}
		//try {
			//add tweet to database and return success or customized exception
		//} catch(DatabaseException e) {
			//throw new DatabaseCustomException(e);
		//}
		//for now i am just loading the cache
		Set<Tweet> userTweet = MetadataServiceCacheManager.getInstance().getUserTweet().get(tweet.getUserId());
		//temporary setting a id
		tweet.setTweetId((long) (Math.random()));
		userTweet.add(tweet);
		return tweet; //ideally this would return a response
	}

}
