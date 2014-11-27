package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.visitor.CountPostsVisitor;

public class TotalPostsCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    CountPostsVisitor visitor = new CountPostsVisitor();
    TwitterEntity.bfs(visitor, TwitterGroup.getRootGroup());
    AdminControl.getInstance().getLblMessage().setText("Total posts: "+visitor.getPosts());
  }
}
