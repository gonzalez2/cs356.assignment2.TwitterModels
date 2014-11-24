package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.csupomona.cs356.twitter.gui.AdminControl;
import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.visitor.CountVisitor;

public class StatsCommand implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    String stats = "<html>Statistics<br />";
    CountVisitor visitor = new CountVisitor();
    TwitterEntity.bfs(visitor, TwitterGroup.getRootGroup());
    stats += "Total Groups: "+visitor.getGroups()+"<br />";
    stats += "Total Users: "+visitor.getUsers()+"<br />";
    stats += "Total Posts: "+visitor.getPosts()+"<br />";
    stats += "Percent of Positive Posts : "+visitor.getPercentPositivePosts()+"<br />";
    AdminControl.getInstance().getLblStats().setText(stats);
  }
}
