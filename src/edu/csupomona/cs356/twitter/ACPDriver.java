package edu.csupomona.cs356.twitter;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

/*
 * Drive the entire program. Create fake groups and users. Show the GUI.
 */
public class ACPDriver {
  /*
   * Singleton Pattern
   */
  public static ACPDriver driver;
  private ACPDriver() {};
  public static ACPDriver getInstance() {
    if (driver == null) driver = new ACPDriver();
    return driver;
  }

  /*
   * Manually create a few groups, then create 25 users who each have 5 posts
   * and are following 5 different users. Start the Admin Control Panel.
   */
  public static void main(String[] args) {
    List<TwitterGroup> groups = new ArrayList<TwitterGroup>();
    List<TwitterUser> users = new ArrayList<TwitterUser>();
    groups.add(TwitterGroup.getRootGroup());
    groups.add(new TwitterGroup("group1", groups.get(0)));
    groups.add(new TwitterGroup("group2", groups.get(1)));
    groups.add(new TwitterGroup("group3", groups.get(2)));
    generateUsers(25, users, groups);
    generateMessages(5, users);
    generateFollowers(5, users);
    ACPDriver.getInstance().start();
  }

  /*
   * Starts the GUI for the Admin Control Panel
   */
  public void start() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AdminControl.getInstance().setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /*
   * Generate users and assign them a random parent group.
   */
  private static void generateUsers(Integer num, List<TwitterUser> users, List<TwitterGroup> groups) {
    for (Integer i = 0; i < num; i++) {
      try {
        TwitterUser temp = new TwitterUser("user"+i, getRandomGroup(groups));
        users.add(temp);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /*
   * Generate 5 random string messages for each user.
   */
  private static void generateMessages(Integer num, List<TwitterUser> users) {
    for (TwitterUser user : users) {
      for (Integer i = 0; i < num; i++) {
        user.post(getRandomString(10));
      }
    }
  }

  /*
   * Follow 5 random users, no duplicates.
   */
  private static void generateFollowers(int num, List<TwitterUser> users) {
    for (TwitterUser user : users) {
      List<TwitterUser> followers = new ArrayList<TwitterUser>();
      followers.add(user);
      for (Integer i = 0; i < num; i++) {
        TwitterUser r = getRandomUser(users, followers);
        followers.add(r);
        try {
          user.follow(r, null);
        } catch (Exception e) {
          //the randomUser generator did not do its job right
          e.printStackTrace();
        }
      }
    }
  }

  /*
   * Return a random group.
   */
  private static TwitterGroup getRandomGroup(List<TwitterGroup> groups) {
    return groups.get(new Random().nextInt(groups.size()));
  }

  /*
   * Return a random user that isn't in the list `followers`.
   */
  private static TwitterUser getRandomUser(List<TwitterUser> users, List<TwitterUser> followers) {
    TwitterUser u;
    do {
      u = users.get(new Random().nextInt(users.size()));
    } while (followers.contains(u));
    return u;
  }

  /*
   * Return a random string of lower-case letters.
   */
  private static String getRandomString(Integer len) {
    String str = "";
    for (Integer i = 0; i < len; i++) {
      str += (char) (new Random().nextInt(26) + 97);
    }
    return str;
  }
}
