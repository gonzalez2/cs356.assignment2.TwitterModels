package edu.csupomona.cs356.twitter;

import java.util.ArrayList;

/*
TwitterGroups keeps track of all groups created in the groups ArrayList. Make 
sure you always have at least one root group. When you create a new group, add
it to the groups list. The default parentGroup is root. Make sure names are
unique.
*/
public class TwitterGroup {
  private static ArrayList<TwitterGroup> groups;
  private String name;
  private TwitterGroup parentGroup;
  
  // Return the root group. If it doesn't exist make it!
  public static TwitterGroup getRootGroup() {
    if (groups.isEmpty()) {
      groups.add(new TwitterGroup());
    }
    return groups.get(0);
  }
  
  // Return a group from the list with the given group name. Null if nothing
  // was found.
  public static TwitterGroup getGroup(String name) {
    for (TwitterGroup group : groups) {
      if (group.getName().compareTo(name) == 0) {
        return group;
      }
    }
    return null;
  }
  
  // Check to make sure the group name passed to this method does not find any
  // other groups in the list with the same name.
  private static boolean checkNameUniqueness(String name) {
    for (TwitterGroup group : groups) {
      if (group.name.compareTo(name) == 0) {
        return false;
      }
    }
    return true;
  }
  
  // Singleton Patter initializer for the root group.
  private TwitterGroup() {
    this.parentGroup = null;
    this.name = "Root";
  }
  
  // TwitterGroup initializer that takes in a name and a parent and sets the
  // private variables, if the name is not unique throw an exception. Add
  // this new group to the groups list.
  public TwitterGroup(String name, TwitterGroup parentGroup) throws Exception {
    this.setParentGroup(parentGroup);
    this.setName(name);
    groups.add(this);
  }
  
  // Set the parent group, if parentGroup is null set it to the root.
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
  
  // Set the name if it is unique, if not throw an exception.
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
  
  // Return the group name...for now.
  public String toString() {
    return this.name;
  }
}
