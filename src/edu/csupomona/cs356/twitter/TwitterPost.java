package edu.csupomona.cs356.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TwitterPost implements Comparable<TwitterPost> {
  private static Integer count = 0;
  private static HashMap<String, List<TwitterPost>> posts = new HashMap<String, List<TwitterPost>>();
  private Integer id;
  private TwitterUser author;
  private String message;

  public static List<TwitterPost> getUserPosts(String uuid) {
    return posts.get(uuid);
  }
  
  public static List<TwitterPost> getUserFeed(String[] uuids) {
    List<TwitterPost> feed = null;
    for (String uuid : uuids) {
      List<TwitterPost> userPosts = getUserPosts(uuid);
      if (feed == null) {
        feed = userPosts;
      } else {
        feed.addAll(userPosts);
      }
    }
    Collections.sort(feed);
    return feed;
  }
  
  public TwitterPost(TwitterUser author, String message) {
    this.id = count++;
    this.author = author;
    this.message = message;
    List<TwitterPost> userPosts = null;
    if (!posts.containsKey(author)) {
       userPosts = new ArrayList<TwitterPost>();
    } else {
      userPosts = posts.get(author);
    }
    userPosts.add(this);
    posts.put(author.getName(), userPosts);
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public TwitterUser getAuthor() {
    //TODO: asdfkasdfa
    return author;
  }
  
  public String toString() {
    return this.getAuthor() + " - " + this.getMessage();
  }
  
  public int compareTo(TwitterPost comparePost) {
    return this.id - comparePost.id;
  }
}
