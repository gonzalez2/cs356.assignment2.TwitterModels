package edu.csupomona.cs356.twitter.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.csupomona.cs356.twitter.observer.TwitterObserver;
import edu.csupomona.cs356.twitter.visitor.TwitterEntityVisitor;
import edu.csupomona.cs356.twitter.visitor.InvalidNameVisitor;

/*
 * Following is a set so the list can be unique.
 */
public class TwitterUser extends TwitterEntity{
  private Set<TwitterUser> following = new HashSet<TwitterUser>();
  private List<TwitterPost> posts = new ArrayList<TwitterPost>();

  /*
   * Try to create a new TwitterUser, if the name is a duplicate throw an
   * Exception. Add the object to the parent's children list. Notify the
   * observers of the addition.
   */
  public TwitterUser(String name, TwitterGroup parentGroup) throws Exception {
    if (name.indexOf(' ') >= 0) {
      throw new Exception("User names cannot contain spaces.");
    }
    InvalidNameVisitor visitor = new InvalidNameVisitor();
    TwitterEntity.bfs(visitor, TwitterGroup.getRootGroup());
    if (visitor.checkInvalidUser(name)) {
      throw new Exception("User already exists.");
    }
    this.name = name;
    this.parent = parentGroup;
    this.parent.children.add(this);
    TwitterGroup.getRootGroup().notifyObservers();
  }

  /*
   * Implement accept to tell the visitor they are visiting a TwitterUser
   */
  public void accept(TwitterEntityVisitor visitor) {
    visitor.visitTwitterUser(this);
  }

  /*
   * Make sure the user is valid then follow the user and attach the observer
   * to that user to get updates on their posts. Then notify our observers.
   */
  public void follow(TwitterUser user, TwitterObserver observer) throws Exception {
    if (this == user) throw new Exception("Cannot add yourself");
    if (!this.following.add(user)) throw new Exception("Invalid follow. Duplicate User");
    if (observer != null) user.attach(observer);
    this.notifyObservers();
  }

  /*
   * Create a new TwitterPost. Notify any attached observers.
   */
  public void post(String message) {
    posts.add(new TwitterPost(message));
    this.notifyObservers();
  }

  /*
   * Return all posts of a given author.
   */
  public List<TwitterPost> getPosts() {
    return this.posts;
  }

  /*
   * Return all posts that would be in the user's feed (anyone they are
   * following and themselves). Sort the posts so the newest is at the top.
   */
  private List<TwitterPost> getFeedList() {
    List<TwitterPost> feed = new ArrayList<TwitterPost>();
    feed.addAll(this.getPosts());
    for (TwitterUser user : this.following) {
      feed.addAll(user.getPosts());
    }
    Collections.sort(feed);
    Collections.reverse(feed);
    return feed;
  }

  /*
   * Turn my following user set into a string array for JList gui object. 
   */
  public String[] getFollowing() {
    String[] following = new String[this.following.size()];
    Integer i = 0;
    for (TwitterUser user : this.following) {
      following[i++] = user.toString();
    }
    return following;
  }

  /*
   * Turn my feed into a string array for JList gui object.
   */
  public String[] getFeed() {
    List<String> myFeed = new ArrayList<String>();
    List<TwitterPost> posts = this.getFeedList();
    for (TwitterPost post : posts) {
      myFeed.add(this.toString()+": "+post.toString());
    }
    return myFeed.toArray(new String[myFeed.size()]);
  }

  /*
   * Special attach function that is called by a user panel when opened. This
   * iterates through my following set and attaches the observer to each user.
   * Also attach to `this` user.
   */
  public void attachUser(TwitterObserver observer) {
    this.attach(observer);
    for (TwitterUser user : this.following) {
      user.attach(observer);
    }
  }

  /*
   * BFS through the tree to find a user of a given `name`(string). Not making
   * this a visitor to keep the lookup time as low as possible since we can
   * return when we find the user, no need to search the entire tree first.
   */
  public static TwitterUser findUser(String name, TwitterGroup parent) {
    for(TwitterEntity entity : parent.children) {
      if (entity.isLeaf()) {
        if (entity.getName().compareTo(name) == 0) return (TwitterUser) entity;
      } else {
        TwitterUser rec = findUser(name, (TwitterGroup) entity);
        if (rec != null) return rec;
      }
    }
    return null;
  }
}
