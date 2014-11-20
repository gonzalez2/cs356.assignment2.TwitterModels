package edu.csupomona.cs356.twitter.models;

import java.util.ArrayList;
import java.util.List;

import edu.csupomona.cs356.twitter.visitor.TwitterEntityVisitor;

public class TwitterUser extends TwitterEntity{
  private List<TwitterUser> following;
  
  public TwitterUser(String name, TwitterGroup parentGroup) throws Exception {
    if (TwitterUser.findUser(name, TwitterGroup.getRootGroup()) != null) {
      throw new Exception("User already exists.");
    }
    this.name = name;
    this.parent = parentGroup;
    this.following = new ArrayList<TwitterUser>();
    this.parent.children.add(this);
  }
  
  public String toString() {
    return this.name;
  }
  
  public void accept(TwitterEntityVisitor visitor) {
    visitor.visitTwitterUser(this);
  }
  
  public boolean follow(TwitterUser user) {
    return this.following.add(user);
  }
  
  public boolean unfollow(TwitterUser user) {
    return this.following.remove(user);
  }
  
  public void post(String message) {
    new TwitterPost(this, message);
  }

  public String[] getFollowing() {
    String[] following = new String[this.following.size()];
    for (Integer i = 0; i < this.following.size(); i++) {
      following[i] = this.following.get(i).toString();
    }
    return following;
  }
  
  public String[] getFeed() {
    List<String> myFeed = new ArrayList<String>();
    List<TwitterPost> posts = TwitterPost.getUserFeed(this);
    for (TwitterPost post : posts) {
      myFeed.add(post.toString());
    }
    return myFeed.toArray(new String[myFeed.size()]);
  }

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
