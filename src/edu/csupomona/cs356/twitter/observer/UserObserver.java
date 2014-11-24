package edu.csupomona.cs356.twitter.observer;

import javax.swing.JList;
import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterUser;

/*
 * Observer for the User panel that updates the feed and following lists when
 * a new follower is added or a post is made from the panel's user or any of
 * their following.
 */
public class UserObserver implements TwitterObserver {
  private JList<String> listFollowing;
  private JList<String> listNewsFeed;
  private TwitterUser user;

  public UserObserver(JList<String> listFollowing, JList<String> listNewsFeed, TwitterUser user) {
    this.listFollowing = listFollowing;
    this.listNewsFeed = listNewsFeed;
    this.user = user;
  }

  @Override
  public void update(TwitterEntity twitterEntity) {
    this.listFollowing.setListData(user.getFollowing());
    this.listNewsFeed.setListData(user.getFeed());
  }
}
