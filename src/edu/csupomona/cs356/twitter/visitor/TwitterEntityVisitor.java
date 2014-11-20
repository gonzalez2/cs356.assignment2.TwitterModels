package edu.csupomona.cs356.twitter.visitor;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public interface TwitterEntityVisitor {
  public void visitTwitterUser(TwitterUser user);
  public void visitTwitterGroup(TwitterGroup group);
}
