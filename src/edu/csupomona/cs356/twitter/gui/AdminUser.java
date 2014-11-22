package edu.csupomona.cs356.twitter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class AdminUser extends JFrame implements ActionListener {

  private static final long serialVersionUID = 3315711711061548224L;
  private TwitterUser user;
  private JPanel userPanel;
  private JTextField fieldFollowUser;
  private JTextField fieldNewMessage;
  private JList<String> listFollowing;
  private JList<String> listNewsFeed;

  public AdminUser(TwitterUser user) {
    this.user = user;
    setBounds(100, 100, 300, 350);
    setTitle(this.user.toString());
    userPanel = new JPanel();
    userPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    userPanel.setLayout(null);
    setContentPane(userPanel);
    
    fieldFollowUser = new JTextField();
    fieldFollowUser.setBounds(6, 6, 216, 28);
    userPanel.add(fieldFollowUser);
    fieldFollowUser.setColumns(10);
    
    JButton btnFollowUser = new JButton("Follow");
    btnFollowUser.setBounds(222, 6, 72, 29);
    btnFollowUser.setActionCommand("follow");
    btnFollowUser.addActionListener(this);
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
    
    JButton btnNewMessage = new JButton("Post");
    btnNewMessage.setBounds(222, 162, 72, 29);
    btnNewMessage.setActionCommand("post_message");
    btnNewMessage.addActionListener(this);
    userPanel.add(btnNewMessage);
    
    listNewsFeed = new JList<String>(this.user.getFeed());
    JScrollPane scrollNewsFeed = new JScrollPane();
    scrollNewsFeed.setBounds(6, 212, 288, 110);
    scrollNewsFeed.setViewportView(listNewsFeed);
    userPanel.add(scrollNewsFeed);
    
    JLabel lblNewsFeed = new JLabel("News Feed");
    lblNewsFeed.setBounds(6, 192, 288, 16);
    userPanel.add(lblNewsFeed);
    
    JLabel lblFollowing = new JLabel("Following");
    lblFollowing.setBounds(6, 34, 288, 16);
    userPanel.add(lblFollowing);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
    case "follow":
      this.user.follow(TwitterUser.findUser(fieldFollowUser.getText(), TwitterGroup.getRootGroup()));
      fieldFollowUser.setText("");
      listFollowing.setListData(this.user.getFollowing());
      listNewsFeed.setListData(this.user.getFeed());
      break;
    case "post_message":
      this.user.post(fieldNewMessage.getText());
      fieldNewMessage.setText("");
      listNewsFeed.setListData(this.user.getFeed());
      break;
    default:
      break;
    }
  }
}
