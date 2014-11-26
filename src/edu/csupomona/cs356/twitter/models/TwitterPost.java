package edu.csupomona.cs356.twitter.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * I initially created a Hash of all the posts so I can comb through them all
 * when creating the statistics but I never used it this way. I should have
 * created the posts List in the User model.
 */
public class TwitterPost implements Comparable<TwitterPost> {
  private static Integer count = 0;
  private static HashMap<String, List<TwitterPost>> posts = new HashMap<String, List<TwitterPost>>();
  private long createdAt = System.currentTimeMillis();
  private Integer id = ++count;
  private TwitterUser author;
  private String message;

  /*
   * Return all posts of a given author.
   */
  public static List<TwitterPost> getUserPosts(String author) {
    if (posts.containsKey(author)) {
      return posts.get(author);
    } else {
      return new ArrayList<TwitterPost>();
    }
  }

  /*
   * Return all posts that would be in the user's feed (anyone they are
   * following and themselves). Sort the posts so the newest is at the top.
   */
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

  /*
   * Iterate the `id` counter and set the appropriate fields. If the posts hash
   * is uninitialized for the author initialize it. Add the post to the hash.
   */
  public TwitterPost(TwitterUser author, String message) {
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

  /*
   * Too lazy to create random strings with actual positive messages, so to
   * simulate this I'm making the character `z` a positive message. So if the
   * message contains `z` it returns true.
   */
  public boolean hasPositiveMessage() {
    return this.message.indexOf('z') >= 0;
  }

  /*
   * Used for sorting. Sorts by id.
   */
  public int compareTo(TwitterPost comparePost) {
    return this.id - comparePost.id;
  }
}
