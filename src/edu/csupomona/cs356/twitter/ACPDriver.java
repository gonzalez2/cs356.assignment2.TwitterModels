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
