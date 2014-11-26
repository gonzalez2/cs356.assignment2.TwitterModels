package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.visitor.RecentUserVisitor;

public class RecentUserCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    RecentUserVisitor visitor = new RecentUserVisitor();
    TwitterEntity.bfs(visitor, TwitterGroup.getRootGroup());
    AdminControl.getInstance().getLblMessage().setText("Most recently updated user: "+visitor.getRecent().getName());
  }
}
