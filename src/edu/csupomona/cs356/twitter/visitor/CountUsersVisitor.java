package edu.csupomona.cs356.twitter.visitor;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

/*
 * Using just one visitor. If I'm doing a BFS on the tree to visit and count
 * each element, I only want to do it once...
 */
public class CountUsersVisitor implements TwitterEntityVisitor {
  private Integer users = 0;

  public Integer getUsers() {
    return this.users;
  }

  @Override
  public void visitTwitterUser(TwitterUser user) {
    this.users += 1;
  }

  @Override
  public void visitTwitterGroup(TwitterGroup group) {}

}
