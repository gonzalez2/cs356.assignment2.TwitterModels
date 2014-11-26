package edu.csupomona.cs356.twitter.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;

import edu.csupomona.cs356.twitter.command.FollowCommand;
import edu.csupomona.cs356.twitter.command.PostMessageCommand;
import edu.csupomona.cs356.twitter.models.TwitterUser;
import edu.csupomona.cs356.twitter.observer.TwitterObserver;
import edu.csupomona.cs356.twitter.observer.UserObserver;

public class AdminUser extends JFrame {

  private static final long serialVersionUID = 3315711711061548224L;
  private TwitterUser user;
  private JPanel userPanel;
  private JTextField fieldFollowUser;
  private JTextField fieldNewMessage;
  private JList<String> listFollowing;
  private JList<String> listNewsFeed;
  private JButton btnFollowUser;
  private JButton btnNewMessage;
  private JLabel lblNewsFeed;
  private JLabel lblFollowing;
  private JScrollPane scrollNewsFeed;
  private JLabel lblUpdatedAt;
  private JLabel lblCreatedAt;
  private TwitterObserver observer;

  public AdminUser(TwitterUser user) {
    this.user = user;
    setBounds(100, 100, 300, 400);
    setTitle(this.user.toString());
    userPanel = new JPanel();
    userPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    userPanel.setLayout(null);
    setContentPane(userPanel);
    
    fieldFollowUser = new JTextField();
    fieldFollowUser.setBounds(6, 6, 216, 28);
    userPanel.add(fieldFollowUser);
    fieldFollowUser.setColumns(10);
    
    btnFollowUser = new JButton("Follow");
    btnFollowUser.setBounds(222, 6, 72, 29);
    btnFollowUser.addActionListener(new FollowCommand(this));
    userPanel.add(btnFollowUser);
    
    listFollowing = new JList<String>(this.user.getFollowing());
    JScrollPane scrollFollowing = new JScrollPane();
    scrollFollowing.setBounds(6, 57, 288, 100);
    scrollFollowing.setViewportView(listFollowing);
    userPanel.add(scrollFollowing);
    
    fieldNewMessage = new JTextField();
    fieldNewMessage.setBounds(6, 162, 216, 28);
    userPanel.add(fieldNewMessage);
    fieldNewMessage.setColumns(10);
    
    btnNewMessage = new JButton("Post");
    btnNewMessage.setBounds(222, 162, 72, 29);
    btnNewMessage.addActionListener(new PostMessageCommand(this));
    userPanel.add(btnNewMessage);
    
    listNewsFeed = new JList<String>(this.user.getFeed());
    scrollNewsFeed = new JScrollPane();
    scrollNewsFeed.setBounds(6, 212, 288, 110);
    scrollNewsFeed.setViewportView(listNewsFeed);
    userPanel.add(scrollNewsFeed);
    
    lblNewsFeed = new JLabel("News Feed");
    lblNewsFeed.setBounds(6, 192, 288, 16);
    userPanel.add(lblNewsFeed);
    
    lblFollowing = new JLabel("Following");
    lblFollowing.setBounds(6, 34, 288, 16);
    userPanel.add(lblFollowing);
    
    lblCreatedAt = new JLabel("Created at: "+this.user.getCreatedAt());
    lblCreatedAt.setBounds(6, 331, 288, 16);
    userPanel.add(lblCreatedAt);
    
    lblUpdatedAt = new JLabel("Updated at: "+this.user.getUpdatedAt());
    lblUpdatedAt.setBounds(6, 359, 288, 16);
    userPanel.add(lblUpdatedAt);
    
    observer = new UserObserver(listFollowing, listNewsFeed, lblUpdatedAt, user);
    this.user.attachUser(observer);
  }

  public TwitterUser getUser() {
    return this.user;
  }

  public TwitterObserver getObserver() {
    return this.observer;
  }

  public JTextField getFieldFollowUser() {
    return this.fieldFollowUser;
  }

  public JTextField getFieldNewMessage() {
    return this.fieldNewMessage;
  }
}
