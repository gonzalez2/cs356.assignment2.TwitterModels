package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterGroup;

public class AddGroupCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    new TwitterGroup(AdminControl.getInstance().getFieldAddGroup().getText(), (TwitterGroup) AdminControl.getInstance().getSelectedEntity());
    AdminControl.getInstance().getFieldAddGroup().setText("");
  }
}
