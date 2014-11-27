package edu.csupomona.cs356.twitter.visitor;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

/*
 * Using just one visitor. If I'm doing a BFS on the tree to visit and count
 * each element, I only want to do it once...
 */
public class CountPostsVisitor implements TwitterEntityVisitor {
  private Integer posts = 0;

  public Integer getPosts() {
    return this.posts;
  }

  @Override
  public void visitTwitterUser(TwitterUser user) {
    this.posts += user.getPosts().size();
  }

  @Override
  public void visitTwitterGroup(TwitterGroup group) {}

}
