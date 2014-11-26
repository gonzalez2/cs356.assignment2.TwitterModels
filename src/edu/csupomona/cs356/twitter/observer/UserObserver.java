package edu.csupomona.cs356.twitter.observer;

import javax.swing.JLabel;
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
  private JLabel lblLastUpdated;
  private TwitterUser user;

  public UserObserver(JList<String> listFollowing, JList<String> listNewsFeed, JLabel lblLastUpdated, TwitterUser user) {
    this.listFollowing = listFollowing;
    this.listNewsFeed = listNewsFeed;
    this.lblLastUpdated = lblLastUpdated;
    this.user = user;
  }

  @Override
  public void update(TwitterEntity twitterEntity) {
    this.listFollowing.setListData(user.getFollowing());
    this.listNewsFeed.setListData(user.getFeed());
    twitterEntity.touch();
    user.touch();
    this.lblLastUpdated.setText("Updated at: "+Long.toString(user.getUpdatedAt()));
  }
}
