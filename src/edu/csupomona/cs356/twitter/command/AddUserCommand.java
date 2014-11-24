package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class AddUserCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    String name = AdminControl.getInstance().getFieldAddUser().getText();
    AdminControl.getInstance().getFieldAddUser().setText("");
    try {
      new TwitterUser(name, (TwitterGroup) AdminControl.getInstance().getSelectedEntity());
    } catch (Exception e1) {
      JOptionPane.showMessageDialog(AdminControl.getInstance(), e1.getMessage()+": "+name);
    }
  }
}
