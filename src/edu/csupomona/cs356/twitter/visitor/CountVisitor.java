package edu.csupomona.cs356.twitter.visitor;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class CountVisitor implements TwitterEntityVisitor {
  private Integer users = 0;
  private Integer groups = 0;

  public Integer getUsers() {
    return users;
  }

  public Integer getGroups() {
    return groups;
  }

  public void reset() {
    this.users = 0;
    this.groups = 0;
  }

  @Override
  public void visitTwitterUser(TwitterUser user) {
    this.users += 1;
  }

  @Override
  public void visitTwitterGroup(TwitterGroup group) {
    this.groups += 1;
  }

}
