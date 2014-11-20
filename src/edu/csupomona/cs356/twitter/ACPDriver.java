package edu.csupomona.cs356.twitter;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class ACPDriver {
  public static ACPDriver driver;
  
  private ACPDriver() {};
  
  public static ACPDriver getInstance() {
    if (driver == null) driver = new ACPDriver();
    return driver;
  }
  
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
  
  private static void generateMessages(Integer num, List<TwitterUser> users) {
    for (TwitterUser user : users) {
      for (Integer i = 0; i < num; i++) {
        user.post(getRandomString(10));
      }
    }
  }
  
  private static void generateFollowers(int num, List<TwitterUser> users) {
    for (TwitterUser user : users) {
      List<TwitterUser> followers = new ArrayList<TwitterUser>();
      followers.add(user);
      for (Integer i = 0; i < num; i++) {
        TwitterUser r = getRandomUser(users, followers);
        followers.add(r);
        user.follow(r);
      }
    }
  }

  private static TwitterGroup getRandomGroup(List<TwitterGroup> groups) {
    return groups.get(new Random().nextInt(groups.size()));
  }
  
  private static TwitterUser getRandomUser(List<TwitterUser> users, List<TwitterUser> followers) {
    TwitterUser u;
    do {
      u = users.get(new Random().nextInt(users.size()));
    } while (followers.contains(u));
    return u;
  }
  
  private static String getRandomString(Integer len) {
    String str = "";
    for (Integer i = 0; i < len; i++) {
      str += (char) (new Random().nextInt(26) + 97);
    }
    return str;
  }

  public void start() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AdminControl frame = new AdminControl();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
