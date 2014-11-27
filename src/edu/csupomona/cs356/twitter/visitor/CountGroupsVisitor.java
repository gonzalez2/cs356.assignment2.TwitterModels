package edu.csupomona.cs356.twitter.visitor;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

/*
 * Using just one visitor. If I'm doing a BFS on the tree to visit and count
 * each element, I only want to do it once...
 */
public class CountGroupsVisitor implements TwitterEntityVisitor {
  private Integer groups = 0;

  public Integer getGroups() {
    return this.groups;
  }

  @Override
  public void visitTwitterUser(TwitterUser user) {}

  @Override
  public void visitTwitterGroup(TwitterGroup group) {
    this.groups += 1;
  }

}
