package edu.csupomona.cs356.twitter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class AdminUser extends JFrame {

  private static final long serialVersionUID = 3315711711061548224L;
  private JPanel contentPanel;
  private JTextField fieldFollowUser;
  private JTextField fieldNewMessage;

  public AdminUser() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 300, 350);
    contentPanel = new JPanel();
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPanel.setLayout(null);
    setContentPane(contentPanel);
    
    fieldFollowUser = new JTextField();
    fieldFollowUser.setBounds(6, 6, 216, 28);
    contentPanel.add(fieldFollowUser);
    fieldFollowUser.setColumns(10);
    
    JButton btnFollowUser = new JButton("Follow");
    btnFollowUser.setBounds(222, 6, 72, 29);
    contentPanel.add(btnFollowUser);
    
    JList<TwitterEntity> listFollowing = new JList<TwitterEntity>();
    listFollowing.setBounds(6, 40, 288, 100);
    contentPanel.add(listFollowing);
    
    fieldNewMessage = new JTextField();
    fieldNewMessage.setBounds(6, 152, 216, 28);
    contentPanel.add(fieldNewMessage);
    fieldNewMessage.setColumns(10);
    
    JButton btnNewMessage = new JButton("Post");
    btnNewMessage.setBounds(222, 152, 72, 29);
    contentPanel.add(btnNewMessage);
    
    JList<TwitterPost> listNewsFeed = new JList<TwitterPost>();
    listNewsFeed.setBounds(6, 192, 288, 130);
    contentPanel.add(listNewsFeed);
  }
}
