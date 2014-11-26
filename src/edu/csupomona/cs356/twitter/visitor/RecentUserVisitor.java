package edu.csupomona.cs356.twitter.visitor;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class RecentUserVisitor implements TwitterEntityVisitor {
  TwitterUser recent = null;

  @Override
  public void visitTwitterUser(TwitterUser user) {
    if (this.recent == null || user.getUpdatedAt() > this.recent.getUpdatedAt()) {
      this.recent = user;
    }
  }

  @Override
  public void visitTwitterGroup(TwitterGroup group) {}

  public TwitterUser getRecent() {
    return this.recent;
  }
}
