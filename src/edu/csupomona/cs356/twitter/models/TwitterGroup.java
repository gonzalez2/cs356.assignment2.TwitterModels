package edu.csupomona.cs356.twitter.models;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import edu.csupomona.cs356.twitter.visitor.TwitterEntityVisitor;

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
  
  // TwitterGroup initializer that takes in a name and a parent and sets the
  // private variables, if the name is not unique throw an exception. Add
  // this new group to the groups list.
  public TwitterGroup(String name, TwitterGroup parentGroup) {
    this.parent = parentGroup;
    this.name = name;
    this.children = new ArrayList<TwitterEntity>();
    this.parent.children.add(this);
  }
  
  // Return the group name...for now.
  public String toString() {
    return this.name;
  }

  public void accept(TwitterEntityVisitor visitor) {
    visitor.visitTwitterGroup(this);
  }

  @Override
  public TreeNode getChildAt(int childIndex) {
    return children.get(childIndex);
  }

  @Override
  public int getChildCount() {
    return children.size();
  }

  @Override
  public boolean getAllowsChildren() {
    return true;
  }

  @Override
  public boolean isLeaf() {
    return false;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Enumeration children() {
    return java.util.Collections.enumeration(this.children);
  }

  @Override
  public void remove(int index) {
    this.children.remove(index);
  }

  @Override
  public void insert(MutableTreeNode child, int index) {
    this.children.add(index, (TwitterGroup) child);
  }

  @Override
  public void remove(MutableTreeNode node) {
    this.children.remove((TwitterGroup) node);
    
  }
}
