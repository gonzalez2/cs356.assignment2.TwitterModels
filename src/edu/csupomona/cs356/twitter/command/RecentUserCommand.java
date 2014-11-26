package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class RecentUserCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    String recent_id = "";
    recent_id = TwitterUser.findRecentUser(0, TwitterGroup.getRootGroup()).getName();
    AdminControl.getInstance().getLblMessage().setText(recent_id);
  }
}
