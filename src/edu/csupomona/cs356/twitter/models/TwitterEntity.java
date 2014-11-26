package edu.csupomona.cs356.twitter.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import edu.csupomona.cs356.twitter.observer.TwitterObserver;
import edu.csupomona.cs356.twitter.visitor.TwitterEntityVisitor;

/*
 * Parent abstract class for TwitterUser and TwitterGroup. Implement 
 * MutableTreeNode to give us our tree functionality. Have a function which 
 * searches this tree (bfs). Observable design pattern implemented.
 */
public abstract class TwitterEntity implements MutableTreeNode, Comparable<TwitterEntity>{
  protected String name;
  protected TwitterGroup parent;
  protected List<TwitterEntity> children = null;
  protected long createdAt = System.currentTimeMillis();
  protected long updatedAt = System.currentTimeMillis();
  private List<TwitterObserver> observers = new ArrayList<TwitterObserver>();

  /*
   * Observer functions
   */
  public void attach(TwitterObserver observer) {
    this.observers.add(observer);
  }
  public void detach(TwitterObserver observer) {
    this.observers.remove(observer);
  }
  public void notifyObservers() {
    for (TwitterObserver observer : observers) {
      observer.update(this);
    }
  }

  /*
   * Abstract functions
   */
  public abstract void accept(TwitterEntityVisitor visitor);
  public String toString() {
    return this.name;
  }
  public String getName() {
    return this.name;
  }
  public long getCreatedAt() {
    return this.createdAt;
  }
  public long getUpdatedAt() {
    return this.updatedAt;
  }
  public void touch() {
    this.updatedAt = System.currentTimeMillis();
  }

  public int compareTo(TwitterEntity b) {
    if ((b instanceof TwitterUser && this instanceof TwitterUser)
     || (b instanceof TwitterGroup && this instanceof TwitterGroup)) {
      return this.name.compareTo(b.name);
    } else {
      if (this instanceof TwitterGroup) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  /*
   * Breath-First Search the tree. This is a recursive function.
   */
  public static void bfs(TwitterEntityVisitor visitor, TwitterGroup parent) {
    parent.accept(visitor);
    for(TwitterEntity entity : parent.children) {
      if (entity.getAllowsChildren()) {
        bfs(visitor, (TwitterGroup) entity);
      } else {
        entity.accept(visitor);
      }
    }
  }

  /*
   * Implement MutableTreeNode methods. I don't user an user object. The rest
   *  of this is straight forward.
   */
  public void setUserObject(Object object) {}
  public TwitterEntity getParent() {
    return parent;
  }
  public int getIndex(TreeNode node) {
    return -1;
  }
  public void setParent(MutableTreeNode newParent) {
    this.parent = (TwitterGroup) newParent;
  }
  public void removeFromParent() {
    this.parent.children.remove(this);
  }
  public boolean isLeaf() {
    return this.children == null;
  }
  public TwitterEntity getChildAt(int childIndex) {
    return this.isLeaf() ? null : this.children.get(childIndex);
  }
  public int getChildCount() {
    return this.getAllowsChildren() ? this.children.size() : 0;
  }
  public boolean getAllowsChildren() {
    return !this.isLeaf();
  }
  public Enumeration<TwitterEntity> children() {
    return this.isLeaf() ? null : java.util.Collections.enumeration(this.children);
  }
  public void remove(int index) {
    if (this.getAllowsChildren()) this.children.remove(index);
  }
  public void insert(MutableTreeNode child, int index) {
    if (this.getAllowsChildren()) this.children.add(index, (TwitterGroup) child);
  }
  public void remove(MutableTreeNode node) {
    if (this.getAllowsChildren()) this.children.remove((TwitterGroup) node);
  }
}
