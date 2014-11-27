package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.visitor.CountGroupsVisitor;

public class TotalGroupsCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    CountGroupsVisitor visitor = new CountGroupsVisitor();
    TwitterEntity.bfs(visitor, TwitterGroup.getRootGroup());
    AdminControl.getInstance().getLblMessage().setText("Total groups: "+visitor.getGroups());
  }
}
