package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.gui.AdminUser;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class ViewUserCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      AdminUser userFrame = new AdminUser((TwitterUser) AdminControl.getInstance().getSelectedEntity());
      userFrame.setVisible(true);
    } catch (Exception err) {
      err.printStackTrace();
    }
  }
}
