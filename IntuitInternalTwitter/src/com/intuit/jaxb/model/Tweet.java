package com.intuit.jaxb.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tweet implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -5807696635404505460L;

		private Long tweetId;
		
		@XmlAttribute(required= true)
		private String status;
		
		@XmlAttribute(required= true)
		private Long userId;
		
		
		public Tweet() {
		
		}
		
		public Tweet(Long tweetId, String status, Long userId) {
			this.tweetId = tweetId;
			this.status = status;
			this.userId = userId;
		}
		
			
	public Long getTweetId() {
		return tweetId;
	}

	public void setTweetId(Long tweetId) {
		this.tweetId = tweetId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
		

}
