package edu.csupomona.cs356.twitter.visitor;

import java.util.List;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterPost;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class CountVisitor implements TwitterEntityVisitor {
  private Integer users = 0;
  private Integer groups = 0;
  private Integer posts = 0;
  private Integer positive_posts = 0;

  public Integer getUsers() {
    return this.users;
  }

  public Integer getGroups() {
    return this.groups;
  }

  public Integer getPosts() {
    return this.posts;
  }

  public Integer getPositivePosts() {
    return this.positive_posts;
  }

  public String getPercentPositivePosts() {
    return Double.toString(Math.round((double) this.positive_posts / (double) this.posts * 100.0));
  }

  @Override
  public void visitTwitterUser(TwitterUser user) {
    this.users += 1;
    List<TwitterPost> posts = TwitterPost.getUserPosts(user.getName());
    for (TwitterPost post : posts) {
      this.posts += 1;
      if (post.hasPositiveMessage()) this.positive_posts += 1;
    }
  }

  @Override
  public void visitTwitterGroup(TwitterGroup group) {
    this.groups += 1;
  }

}
