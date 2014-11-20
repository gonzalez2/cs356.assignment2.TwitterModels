package edu.csupomona.cs356.twitter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class AdminControl extends JFrame implements ActionListener{

  private static final long serialVersionUID = -4249943349867697788L;
  private JPanel contentPanel;
  private JTextField fieldAddUser;
  private JTextField fieldAddGroup;
  private TwitterEntity selectedEntity;
  private JTree treeGroups;

  public AdminControl() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    setTitle("Admin Control Panel");
    contentPanel = new JPanel();
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPanel);
    contentPanel.setLayout(null);
    
    JButton btnAddUser = new JButton("Add User");
    btnAddUser.setEnabled(false);
    btnAddUser.setBounds(327, 6, 117, 29);
    btnAddUser.setActionCommand("add_user");
    btnAddUser.addActionListener(this);
    contentPanel.add(btnAddUser);
    
    JLabel lblSelectedEntity = new JLabel("");
    lblSelectedEntity.setBounds(188, 93, 134, 16);
    contentPanel.add(lblSelectedEntity);
    
    JButton btnAddGroup = new JButton("Add Group");
    btnAddGroup.setEnabled(false);
    btnAddGroup.setBounds(327, 47, 117, 29);
    btnAddGroup.setActionCommand("add_group");
    btnAddGroup.addActionListener(this);
    contentPanel.add(btnAddGroup);
    
    JButton btnViewUser = new JButton("View User");
    btnViewUser.setEnabled(false);
    btnViewUser.setBounds(327, 88, 117, 29);
    btnViewUser.setActionCommand("view_user");
    btnViewUser.addActionListener(this);
    contentPanel.add(btnViewUser);
    
    fieldAddUser = new JTextField();
    fieldAddUser.setEnabled(false);
    fieldAddUser.setBounds(188, 7, 134, 28);
    contentPanel.add(fieldAddUser);
    fieldAddUser.setColumns(10);
    
    fieldAddGroup = new JTextField();
    fieldAddGroup.setEnabled(false);
    fieldAddGroup.setBounds(188, 48, 134, 28);
    contentPanel.add(fieldAddGroup);
    fieldAddGroup.setColumns(10);
    
    JButton btnTotalMessages = new JButton("Total Messages");
    btnTotalMessages.setBounds(188, 243, 124, 29);
    btnTotalMessages.setActionCommand("tot_messages");
    btnTotalMessages.addActionListener(this);
    contentPanel.add(btnTotalMessages);
    
    JButton btnTotalUsers = new JButton("Total Users");
    btnTotalUsers.setBounds(188, 202, 124, 29);
    btnTotalUsers.setActionCommand("tot_users");
    btnTotalUsers.addActionListener(this);
    contentPanel.add(btnTotalUsers);
    
    JButton btnPercentPositive = new JButton("Percent Positive");
    btnPercentPositive.setBounds(310, 243, 134, 29);
    btnPercentPositive.setActionCommand("percent_positive");
    btnPercentPositive.addActionListener(this);
    contentPanel.add(btnPercentPositive);
    
    JButton btnTotalGroups = new JButton("Total Groups");
    btnTotalGroups.setBounds(310, 202, 134, 29);
    btnTotalGroups.setActionCommand("tot_groups");
    btnTotalGroups.addActionListener(this);
    contentPanel.add(btnTotalGroups);
    
    treeGroups = new JTree(new DefaultTreeModel(TwitterGroup.getRootGroup()));
    treeGroups.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        selectedEntity = (TwitterEntity)treeGroups.getLastSelectedPathComponent();
        if (selectedEntity != null) {
          lblSelectedEntity.setText(selectedEntity.toString());
          if (selectedEntity.isLeaf()) {
            btnViewUser.setEnabled(true);
            btnAddUser.setEnabled(false);
            btnAddGroup.setEnabled(false);
            fieldAddUser.setEnabled(false);
            fieldAddGroup.setEnabled(false);
          } else {
            btnViewUser.setEnabled(false);
            btnAddUser.setEnabled(true);
            btnAddGroup.setEnabled(true);
            fieldAddUser.setEnabled(true);
            fieldAddGroup.setEnabled(true);
          }
        } else {
          lblSelectedEntity.setText("");
          btnViewUser.setEnabled(false);
          btnAddUser.setEnabled(false);
          btnAddGroup.setEnabled(false);
          fieldAddUser.setEnabled(false);
          fieldAddGroup.setEnabled(false);
        }
      }
    });
    JScrollPane treeScroll = new JScrollPane();
    treeScroll.setBounds(6, 6, 170, 266);
    treeScroll.setViewportView(treeGroups);
    contentPanel.add(treeScroll);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
    case "add_user":
      try {
        new TwitterUser(fieldAddUser.getText(), (TwitterGroup) selectedEntity);
        fieldAddUser.setText("");
        ((DefaultTreeModel)treeGroups.getModel()).reload(TwitterGroup.getRootGroup());
      } catch (Exception e1) {
        fieldAddUser.setText(e1.getMessage());
      }
      break;
    case "add_group":
      new TwitterGroup(fieldAddGroup.getText(), (TwitterGroup) selectedEntity);
      fieldAddGroup.setText("");
      ((DefaultTreeModel)treeGroups.getModel()).reload(TwitterGroup.getRootGroup());
      break;
    case "view_user":
      try {
        AdminUser userFrame = new AdminUser((TwitterUser) selectedEntity);
        userFrame.setVisible(true);
      } catch (Exception err) {
        err.printStackTrace();
      }
      break;
    case "tot_users":
      
      break;
    case "tot_groups":
      break;
    case "tot_messages":
      break;
    case "percent_positive":
      break;
    default:
      break;
    }
  }
}
