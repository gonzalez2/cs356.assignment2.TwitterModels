package edu.csupomona.cs356.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TwitterPost implements Comparable<TwitterPost> {
  private static Integer count = 0;
  private static HashMap<String, List<TwitterPost>> posts = new HashMap<String, List<TwitterPost>>();
  private Integer id;
  private String author_uuid;
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
  
  public TwitterPost(String author_uuid, String message) {
    this.id = count++;
    this.author_uuid = author_uuid;
    this.message = message;
    List<TwitterPost> userPosts = null;
    if (!posts.containsKey(author_uuid)) {
       userPosts = new ArrayList<TwitterPost>();
    } else {
      userPosts = posts.get(author_uuid);
    }
    userPosts.add(this);
    posts.put(author_uuid, userPosts);
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public TwitterUser getAuthor() {
    return TwitterUser.find(this.author_uuid);
  }
  
  public int compareTo(TwitterPost comparePost) {
    return this.id - comparePost.id;
  }
}
