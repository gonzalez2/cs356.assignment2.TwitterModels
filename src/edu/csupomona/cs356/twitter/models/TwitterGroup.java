package edu.csupomona.cs356.twitter.models;

import java.util.ArrayList;

import edu.csupomona.cs356.twitter.visitor.TwitterEntityVisitor;
import edu.csupomona.cs356.twitter.visitor.InvalidNameVisitor;

/*
TwitterGroups keeps track of all groups created in the groups ArrayList. Make 
sure you always have at least one root group. When you create a new group, add
it to the groups list. The default parentGroup is root. Make sure names are
unique.
*/
public class TwitterGroup extends TwitterEntity {
  private static TwitterGroup root = new TwitterGroup();

  // Return the root group. If it doesn't exist make it!
  public static TwitterGroup getRootGroup() {
    return root;
  }

  // Pseudo-Singleton Pattern initializer for the root group.
  private TwitterGroup() {
    this.parent = null;
    this.name = "Root";
    this.children = new ArrayList<TwitterEntity>();
  }

  /*
   * TwitterGroup initializer that takes in a name and a parent and sets the
   * private variables, if the name is not unique throw an exception. Add
   * this new group to the groups list.
   */
  public TwitterGroup(String name, TwitterGroup parentGroup) throws Exception {
    if (name.indexOf(' ') >= 0) {
      throw new Exception("Group names cannot contain spaces.");
    }
    InvalidNameVisitor visitor = new InvalidNameVisitor();
    TwitterEntity.bfs(visitor, TwitterGroup.getRootGroup());
    if (visitor.checkInvalidGroup(name)) {
      throw new Exception("Group already exists.");
    }
    this.parent = parentGroup;
    this.name = name;
    this.children = new ArrayList<TwitterEntity>();
    this.parent.children.add(this);
    TwitterGroup.getRootGroup().notifyObservers();
  }

  // Accept the visitor.
  public void accept(TwitterEntityVisitor visitor) {
    visitor.visitTwitterGroup(this);
  }
}
