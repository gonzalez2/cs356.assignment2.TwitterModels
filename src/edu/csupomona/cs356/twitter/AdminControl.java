package edu.csupomona.cs356.twitter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AdminControl extends JFrame {

  private static final long serialVersionUID = -4249943349867697788L;
  private JPanel contentPanel;
  private JTextField fieldAddUser;
  private JTextField fieldAddGroup;

  public AdminControl() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPanel = new JPanel();
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPanel);
    contentPanel.setLayout(null);
    
    JButton btnAddUser = new JButton("Add User");
    btnAddUser.setBounds(327, 6, 117, 29);
    contentPanel.add(btnAddUser);
    
    JLabel lblSelectedEntity = new JLabel("");
    lblSelectedEntity.setBounds(188, 93, 134, 16);
    contentPanel.add(lblSelectedEntity);
    
    JTree treeGroups = new JTree(TwitterGroup.getRootGroup());
    treeGroups.setBounds(6, 6, 170, 266);
    treeGroups.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        TwitterEntity selectedEntity = (TwitterEntity)treeGroups.getLastSelectedPathComponent();
        lblSelectedEntity.setText(selectedEntity.toString());
      }
    });
    contentPanel.add(treeGroups);
    
    JButton btnAddGroup = new JButton("Add Group");
    btnAddGroup.setBounds(327, 47, 117, 29);
    contentPanel.add(btnAddGroup);
    
    JButton btnViewUser = new JButton("View User");
    btnViewUser.setBounds(327, 88, 117, 29);
    contentPanel.add(btnViewUser);
    
    fieldAddUser = new JTextField();
    fieldAddUser.setBounds(188, 7, 134, 28);
    contentPanel.add(fieldAddUser);
    fieldAddUser.setColumns(10);
    
    fieldAddGroup = new JTextField();
    fieldAddGroup.setBounds(188, 48, 134, 28);
    contentPanel.add(fieldAddGroup);
    fieldAddGroup.setColumns(10);
    
    JButton btnTotalMessages = new JButton("Total Messages");
    btnTotalMessages.setBounds(188, 243, 124, 29);
    contentPanel.add(btnTotalMessages);
    
    JButton btnTotalUsers = new JButton("Total Users");
    btnTotalUsers.setBounds(188, 202, 124, 29);
    contentPanel.add(btnTotalUsers);
    
    JButton btnPercentPositive = new JButton("Percent Positive");
    btnPercentPositive.setBounds(310, 243, 134, 29);
    contentPanel.add(btnPercentPositive);
    
    JButton btnTotalGroups = new JButton("Total Groups");
    btnTotalGroups.setBounds(310, 202, 134, 29);
    contentPanel.add(btnTotalGroups);
  }
}
