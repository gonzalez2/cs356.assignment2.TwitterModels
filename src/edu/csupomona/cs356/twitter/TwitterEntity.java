package edu.csupomona.cs356.twitter;

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public abstract class TwitterEntity implements MutableTreeNode{
  protected String name;
  protected TwitterGroup parent;
  protected List<TwitterEntity> children;

  public abstract String toString();
  public String getName() {
    return name;
  }
  public TwitterEntity getParent() {
    return parent;
  }

  @Override
  public int getIndex(TreeNode node) {
    return -1;
  }

  @Override
  public void setParent(MutableTreeNode newParent) {
    this.parent = (TwitterGroup) newParent;
    
  }

  @Override
  public void removeFromParent() {
    this.parent.children.remove(this);
  }

  @Override
  public void setUserObject(Object object) {
    // TODO Auto-generated method stub
  }

  @Override
  public TreeNode getChildAt(int childIndex) {
    return null;
  }

  @Override
  public int getChildCount() {
    return -1;
  }

  @Override
  public boolean getAllowsChildren() {
    return false;
  }

  @Override
  public boolean isLeaf() {
    return true;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Enumeration children() {
    return null;
  }

  @Override
  public void remove(int index) {
  }

  @Override
  public void insert(MutableTreeNode child, int index) {
  }

  @Override
  public void remove(MutableTreeNode node) {
  }
}
