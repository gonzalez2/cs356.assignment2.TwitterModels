package edu.csupomona.cs356.twitter.gui;

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

import edu.csupomona.cs356.twitter.command.*;
import edu.csupomona.cs356.twitter.models.TwitterEntity;
import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.observer.TwitterObserver;
import edu.csupomona.cs356.twitter.observer.RootObserver;

/*
 * Main control panel for the program. Use some getter functions so Command
 * models can use them. The rest is GUI stuff.
 */
public class AdminControl extends JFrame implements TreeSelectionListener{
  private static AdminControl instance = new AdminControl();
  private static final long serialVersionUID = -4249943349867697788L;

  public static AdminControl getInstance() {
    return instance;
  }

  private JPanel contentPanel;
  private JButton btnAddUser;
  private JButton btnAddGroup;
  private JButton btnViewUser;
  private JButton btnTotalPosts;
  private JButton btnValidateIDs;
  private JButton btnRecentUser;
  private JLabel lblSelectedEntity;
  private JScrollPane treeScroll;
  private JLabel lblMessage;
  private JTree treeGroups;
  private JTextField fieldAddUser;
  private JTextField fieldAddGroup;
  private TwitterEntity selectedEntity;
  private JButton btnPositivePosts;
  private JButton btnTotalGroups;
  private JButton btnTotalUsers;

  private AdminControl() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 341);
    setTitle("Admin Control Panel");

    contentPanel = new JPanel();
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPanel);
    contentPanel.setLayout(null);

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

    btnAddUser = new JButton("Add User");
    btnAddUser.setEnabled(false);
    btnAddUser.setBounds(327, 6, 117, 29);
    btnAddUser.addActionListener(new AddUserCommand());
    contentPanel.add(btnAddUser);

    btnAddGroup = new JButton("Add Group");
    btnAddGroup.setEnabled(false);
    btnAddGroup.setBounds(327, 47, 117, 29);
    btnAddGroup.addActionListener(new AddGroupCommand());
    contentPanel.add(btnAddGroup);

    btnViewUser = new JButton("View User");
    btnViewUser.setEnabled(false);
    btnViewUser.setBounds(327, 88, 117, 29);
    btnViewUser.addActionListener(new ViewUserCommand());
    contentPanel.add(btnViewUser);

    btnPositivePosts = new JButton("Positive Posts");
    btnPositivePosts.setBounds(314, 243, 130, 29);
    btnPositivePosts.addActionListener(new PositivePostsCommand());
    contentPanel.add(btnPositivePosts);

    btnTotalGroups = new JButton("Total Groups");
    btnTotalGroups.setBounds(314, 202, 130, 29);
    btnTotalGroups.addActionListener(new TotalGroupsCommand());
    contentPanel.add(btnTotalGroups);

    btnTotalUsers = new JButton("Total Users");
    btnTotalUsers.setBounds(188, 202, 130, 29);
    btnTotalUsers.addActionListener(new TotalUsersCommand());
    contentPanel.add(btnTotalUsers);

    btnTotalPosts = new JButton("Total Posts");
    btnTotalPosts.setBounds(188, 243, 130, 29);
    btnTotalPosts.addActionListener(new TotalPostsCommand());
    contentPanel.add(btnTotalPosts);

    btnValidateIDs = new JButton("Validate IDs");
    btnValidateIDs.setBounds(188, 284, 130, 29);
    btnValidateIDs.addActionListener(new ValidateIDsCommand());
    contentPanel.add(btnValidateIDs);

    btnRecentUser = new JButton("Recent User");
    btnRecentUser.setBounds(314, 284, 130, 29);
    btnRecentUser.addActionListener(new RecentUserCommand());
    contentPanel.add(btnRecentUser);

    lblSelectedEntity = new JLabel("");
    lblSelectedEntity.setBounds(188, 93, 134, 16);
    contentPanel.add(lblSelectedEntity);

    lblMessage = new JLabel("");
    lblMessage.setBounds(188, 129, 256, 61);
    contentPanel.add(lblMessage);

    treeGroups = new JTree(new DefaultTreeModel(TwitterGroup.getRootGroup()));
    treeGroups.addTreeSelectionListener(this);
    treeScroll = new JScrollPane();
    treeScroll.setBounds(6, 6, 170, 307);
    treeScroll.setViewportView(treeGroups);
    contentPanel.add(treeScroll);

    TwitterObserver obs = new RootObserver(treeGroups);
    TwitterGroup.getRootGroup().attach(obs);
  }

  /*
   * Show/Hide buttons/textfields based on what is selected in the JTree
   */
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

  public JLabel getLblMessage() {
    return lblMessage;
  }

  public JTree getTreeGroups() {
    return treeGroups;
  }

  public JTextField getFieldAddUser() {
    return fieldAddUser;
  }

  public JTextField getFieldAddGroup() {
    return fieldAddGroup;
  }

  public TwitterEntity getSelectedEntity() {
    return selectedEntity;
  }
}
