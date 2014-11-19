package edu.csupomona.cs356.twitter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;

public class AdminUser extends JFrame {

  private static final long serialVersionUID = 3315711711061548224L;
  private TwitterUser user;
  private JPanel userPanel;
  private JTextField fieldFollowUser;
  private JTextField fieldNewMessage;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public AdminUser(TwitterUser user) {
    this.user = user;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 300, 350);
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
    userPanel.add(btnFollowUser);
    
    JList listFollowing = new JList(this.user.getFollowing());
    listFollowing.setBounds(6, 57, 288, 100);
    userPanel.add(listFollowing);
    
    fieldNewMessage = new JTextField();
    fieldNewMessage.setBounds(6, 162, 216, 28);
    userPanel.add(fieldNewMessage);
    fieldNewMessage.setColumns(10);
    
    JButton btnNewMessage = new JButton("Post");
    btnNewMessage.setBounds(222, 162, 72, 29);
    userPanel.add(btnNewMessage);
    
    JList listNewsFeed = new JList(this.user.getFeed());
    listNewsFeed.setBounds(6, 212, 288, 110);
    userPanel.add(listNewsFeed);
    
    JLabel lblNewsFeed = new JLabel("News Feed");
    lblNewsFeed.setBounds(6, 192, 288, 16);
    userPanel.add(lblNewsFeed);
    
    JLabel lblFollowing = new JLabel("Following");
    lblFollowing.setBounds(6, 34, 288, 16);
    userPanel.add(lblFollowing);
  }
}
