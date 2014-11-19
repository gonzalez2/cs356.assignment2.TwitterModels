package edu.csupomona.cs356.twitter;

import java.util.ArrayList;
import java.util.List;

public class TwitterUser extends TwitterEntity{
  private List<TwitterUser> following;
  
  public TwitterUser(String name, TwitterGroup parentGroup) {
    this.name = name;
    this.parent = parentGroup;
    this.following = new ArrayList<TwitterUser>();
    this.parent.children.add(this);
  }
  
  public String toString() {
    return this.name;
  }
  
  public boolean follow(TwitterUser user) {
    return this.following.add(user);
  }
  
  public boolean unfollow(TwitterUser user) {
    return this.following.remove(user);
  }
  
  public boolean post(String message) {
    
    return false;
  }

  public String[] getFollowing() {
    return (String[]) this.following.toArray();
  }
  
  public String[] getFeed() {
    String[] myFeed;
    String[] followingNme = (String[]) this.following.toArray();
    followingNme[this.following.size()] = this.name;
    List<TwitterPost> posts = TwitterPost.getUserFeed(followingNme);
    myFeed = new String[posts.size()];
    for (Integer i = 0; i < posts.size(); i++) {
      myFeed[i] = posts.get(i).toString();
    }
    return myFeed;
  }
}
