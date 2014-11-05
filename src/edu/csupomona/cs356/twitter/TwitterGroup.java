package edu.csupomona.cs356.twitter;

import java.util.ArrayList;

public class TwitterGroup {
  private static ArrayList<TwitterGroup> groups;
  private String name;
  private TwitterGroup parentGroup;
  
  public static TwitterGroup getRootGroup() {
    if (groups.isEmpty()) {
      groups.add(new TwitterGroup());
    }
    return groups.get(0);
  }
  
  private static boolean checkNameUniqueness(String name) {
    for (TwitterGroup group : groups) {
      if (group.name.compareTo(name) == 0) {
        return false;
      }
    }
    return true;
  }
  
  private TwitterGroup() {
    this.parentGroup = null;
    this.name = "Root";
  }
  
  public TwitterGroup(String name, TwitterGroup parentGroup) throws Exception {
    this.setParentGroup(parentGroup);
    this.setName(name);
  }
  
  public void setParentGroup(TwitterGroup parentGroup) {
    if (groups.isEmpty() || parentGroup == null) {
      TwitterGroup root = getRootGroup();
      if (parentGroup == null) {
        parentGroup = root;
      }
    }
    this.parentGroup = parentGroup;
  }
  
  public TwitterGroup getParentGroup() {
    return this.parentGroup;
  }
  
  public void setName(String name) throws Exception {
    if (checkNameUniqueness(name)) {
      this.name = name;
    } else {
      throw new Exception("Duplicate name.");
    }
  }
  
  public String getName() {
    return this.name;
  }
  
  public String toString() {
    return this.name;
  }
}
