package edu.csupomona.cs356.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwitterUser implements TwitterEntity{
  private static HashMap<String, TwitterUser> users = new HashMap<String, TwitterUser>();
  private String name;
  private TwitterGroup parentGroup;
  private List<String> followers;
  private List<String> following;
  
  public static TwitterUser find(String name) {
    return users.get(name);
  }
  
  // Check to make sure the group name passed to this method does not find any
  // other groups in the list with the same name.
  private static boolean checkNameUniqueness(String name) {
    return users.containsKey(name);
  }
  
  public TwitterUser(String name, TwitterGroup parentGroup) throws Exception {
    this.setName(name);
    this.setParent(parentGroup);
    this.followers = new ArrayList<String>();
    this.following = new ArrayList<String>();
    users.put(name, this);
  }
  
  public String getName() {
    return this.name;
  }
  
  public TwitterGroup getParent() {
    return this.parentGroup;
  }
  
  public String toString() {
    return this.name;
  }
  
  public void setName(String name) throws Exception {
    if (checkNameUniqueness(name)) {
      this.name = name;
    } else {
      throw new Exception("Duplicate name in TwitterUser::setName: "+name);
    }
  }
  
  public void setParent(TwitterGroup parentGroup) {
    if (parentGroup == null) {
      parentGroup = TwitterGroup.getRootGroup();
    }
    this.parentGroup = parentGroup;
  }
  
  public boolean follow(TwitterUser user) {
    if (this.following.add(user.name)) {
      if (user.followers.add(this.name)) {
        return true;
      } else {
        this.following.remove(user);
      }
    }
    return false;
  }
  
  public boolean unfollow(TwitterUser user) {
    if (this.following.remove(user.name)) {
      if (user.followers.remove(this.name)) {
        return true;
      } else {
        this.following.add(user.name);
      }
    }
    return false;
  }
  
  public boolean post(String message) {
    
    return false;
  }
}
