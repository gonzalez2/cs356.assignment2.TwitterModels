package edu.csupomona.cs356.twitter.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.csupomona.cs356.twitter.observer.TwitterObserver;
import edu.csupomona.cs356.twitter.visitor.TwitterEntityVisitor;

/*
 * Following is a set so the list can be unique.
 */
public class TwitterUser extends TwitterEntity{
  private Set<TwitterUser> following;

  /*
   * Try to create a new TwitterUser, if the name is a duplicate throw an
   * Exception. Add the object to the parent's children list. Notify the
   * observers of the addition.
   */
  public TwitterUser(String name, TwitterGroup parentGroup) throws Exception {
    if (TwitterUser.findUser(name, TwitterGroup.getRootGroup()) != null) {
      throw new Exception("User already exists.");
    } else if (name.indexOf(' ') >= 0) {
      throw new Exception("User names cannot contain spaces.");
    }
    this.name = name;
    this.parent = parentGroup;
    this.following = new HashSet<TwitterUser>();
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
    new TwitterPost(this, message);
    this.notifyObservers();
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
    List<TwitterPost> posts = TwitterPost.getUserFeed(this);
    for (TwitterPost post : posts) {
      myFeed.add(post.toString());
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
   * BFS through the tree to find a user of a given `name`(string)
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

  /*
   * BFS through the tree to find a user of a given `name`(string)
   */
  public static TwitterUser findRecentUser(long time, TwitterGroup parent) {
    TwitterUser recent = null;
    List<TwitterGroup> localGroups = new ArrayList<TwitterGroup>();
    for (TwitterEntity entity : parent.children) {
      if (entity.isLeaf()) {
        if (entity.getUpdatedAt() > time) {
          time = entity.getUpdatedAt();
          recent = (TwitterUser) entity;
        }
      } else {
        localGroups.add((TwitterGroup) entity);
      }
    }
    for (TwitterGroup group : localGroups) {
      TwitterUser remoteRecent = findRecentUser(time, group);
      if (remoteRecent != null && remoteRecent.getCreatedAt() > recent.getCreatedAt()) {
        recent = remoteRecent;
      }
    }
    return recent;
  }
}
