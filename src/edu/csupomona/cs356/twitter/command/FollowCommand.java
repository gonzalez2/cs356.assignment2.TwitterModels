package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.csupomona.cs356.twitter.gui.AdminUser;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class FollowCommand implements ActionListener {
  private AdminUser ucp;

  public FollowCommand(AdminUser ucp) {
    this.ucp = ucp;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String name = ucp.getFieldFollowUser().getText();
    TwitterUser user = TwitterUser.findUser(name, TwitterGroup.getRootGroup());
    if (user == null) JOptionPane.showMessageDialog(ucp, "Couldn't find user: "+name);
    try {
      ucp.getUser().follow(user, ucp.getObserver());
    } catch (Exception e1) {
      JOptionPane.showMessageDialog(ucp, e1.getMessage()+": "+name);
    }
    ucp.getFieldFollowUser().setText("");
  }
}
