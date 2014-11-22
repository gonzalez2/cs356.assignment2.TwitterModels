package edu.csupomona.cs356.twitter.models;

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

  public static List<TwitterPost> getUserPosts(String author) {
    if (posts.containsKey(author)) {
      return posts.get(author);
    } else {
      System.out.println("author not found: "+author);
      return new ArrayList<TwitterPost>();
    }
  }
  
  public static List<TwitterPost> getUserFeed(TwitterUser user) {
    List<TwitterPost> feed = new ArrayList<TwitterPost>();
    feed.addAll(getUserPosts(user.getName()));
    for (String name : user.getFollowing()) {
      List<TwitterPost> userPosts = getUserPosts(name);
      feed.addAll(userPosts);
    }
    Collections.sort(feed);
    Collections.reverse(feed);
    return feed;
  }
  
  public TwitterPost(TwitterUser author, String message) {
    this.id = count++;
    this.author = author;
    this.message = message;
    List<TwitterPost> userPosts = null;
    if (!posts.containsKey(author.getName())) {
       userPosts = new ArrayList<TwitterPost>();
    } else {
      userPosts = posts.get(author.getName());
    }
    userPosts.add(this);
    posts.put(author.getName(), userPosts);
  }
  
  public String toString() {
    return this.author.getName() + " - " + this.message;
  }
  
  public boolean hasPositiveMessage() {
    return this.message.indexOf('z') >= 0;
  }
  
  public int compareTo(TwitterPost comparePost) {
    return this.id - comparePost.id;
  }
}
