package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.visitor.InvalidNameVisitor;

public class ValidateIDsCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    InvalidNameVisitor visitor = new InvalidNameVisitor();
    TwitterEntity.bfs(visitor, TwitterGroup.getRootGroup());
    AdminControl.getInstance().getLblMessage().setText("<html>Array of invalid IDs:<br />"+visitor.getInvalid().toString());
  }
}
