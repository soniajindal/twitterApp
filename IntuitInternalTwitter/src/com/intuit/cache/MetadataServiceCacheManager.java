package com.intuit.cache;

import java.util.HashSet;
/**
 * @author sjindal
 * This class is used to load All the cache and also used during refreshing the cache
 */
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.intuit.jaxb.model.Tweet;

public class MetadataServiceCacheManager {
	
	
	private static MetadataServiceCacheManager s_metadataServiceCacheManager = null;
	private Map<Long, Set<Long>> userFollowerIds = new ConcurrentHashMap<Long, Set<Long>>();
	private Map<Long, Set<Long>> userFollows = new ConcurrentHashMap<Long, Set<Long>>();
	private Map<Long, Set<Tweet>> userTweet = new ConcurrentHashMap<Long, Set<Tweet>>();

	public static MetadataServiceCacheManager getInstance(){
         if(s_metadataServiceCacheManager == null) {
        	 s_metadataServiceCacheManager = new MetadataServiceCacheManager();
          }
          return s_metadataServiceCacheManager;

    }
    
    private MetadataServiceCacheManager(){
    }
    
    
    //the loading should ideally call DB and build cache on top of it
    public void loadAllCache(){
    	loadUserFollowerIds();
    	loadUserFollows();
    	loadUserTweet();

    }

	private void loadUserTweet() {
		Set<Tweet> tweetDetails = new HashSet<Tweet>();
		Tweet tweet = new Tweet(1L, "My first Tweet", 1L);
		tweetDetails.add(tweet);
		tweet = new Tweet(2L, "My second Tweet", 1L);
		tweetDetails.add(tweet);
		tweet = new Tweet(3L, "My third Tweet", 1L);
		tweetDetails.add(tweet);
		userTweet.put(1L, tweetDetails);
		
		tweetDetails = new HashSet<Tweet>();
		tweet = new Tweet(1L, "My first Tweet", 2L);
		tweetDetails.add(tweet);
		tweet = new Tweet(2L, "My second Tweet", 2L);
		tweetDetails.add(tweet);
		tweet = new Tweet(3L, "My third Tweet", 2L);
		tweetDetails.add(tweet);
		userTweet.put(2L, tweetDetails);
	}

	private void loadUserFollows() {
		Set<Long> followsIds = new HashSet<Long>();
		followsIds.add(2L);
		userFollows.putIfAbsent(1L, followsIds);
		followsIds = new HashSet<Long>();
		followsIds.add(1L);
		userFollows.putIfAbsent(2L, followsIds);
	}

	private void loadUserFollowerIds() {
		Set<Long> followerIds = new HashSet<Long>();
		followerIds.add(1L);
		userFollowerIds.putIfAbsent(2L, followerIds);
		followerIds = new HashSet<Long>();
		followerIds.add(2L);
		userFollowerIds.putIfAbsent(1L, followerIds);
	}

	public Map<Long, Set<Long>> getUserFollowerIds() {
		return userFollowerIds;
	}

	public void setUserFollowerIds(Map<Long, Set<Long>> userFollowerIds) {
		this.userFollowerIds = userFollowerIds;
	}

	public Map<Long, Set<Long>> getUserFollows() {
		return userFollows;
	}

	public void setUserFollows(Map<Long, Set<Long>> userFollows) {
		this.userFollows = userFollows;
	}

	public Map<Long, Set<Tweet>> getUserTweet() {
		return userTweet;
	}

	public void setUserTweet(Map<Long, Set<Tweet>> userTweet) {
		this.userTweet = userTweet;
	}
    
    

}
