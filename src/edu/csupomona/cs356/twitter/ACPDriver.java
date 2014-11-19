package edu.csupomona.cs356.twitter;

import java.awt.EventQueue;

public class ACPDriver {
  public static ACPDriver driver;
  
  private ACPDriver() {};
  
  public static ACPDriver getInstance() {
    if (driver == null) driver = new ACPDriver();
    return driver;
  }
  
  public static void main(String[] args) {
    TwitterGroup root = TwitterGroup.getRootGroup();
    TwitterGroup g1 = new TwitterGroup("group1", root);
    TwitterGroup g2 = new TwitterGroup("group2", g1);
    TwitterGroup g3 = new TwitterGroup("group3", g2);
    TwitterUser me = new TwitterUser("Lewis", root);
    TwitterUser u1 = new TwitterUser("user1", root);
    TwitterUser u2 = new TwitterUser("user2", g1);
    TwitterUser u3 = new TwitterUser("user3", g1);
    TwitterUser u4 = new TwitterUser("user4", g2);
    TwitterUser u5 = new TwitterUser("user5", g2);
    TwitterUser u6 = new TwitterUser("user6", g3);
    TwitterUser u7 = new TwitterUser("user7", g3);
    ACPDriver.getInstance().start();
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
