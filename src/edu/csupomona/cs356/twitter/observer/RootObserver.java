package edu.csupomona.cs356.twitter.observer;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import edu.csupomona.cs356.twitter.models.TwitterEntity;

/*
 * Used when adding a user or group to the tree.
 */
public class RootObserver implements TwitterObserver {
  private JTree tree;

  public RootObserver(JTree tree) {
    this.tree = tree;
  }

  @Override
  public void update(TwitterEntity twitterEntity) {
    ((DefaultTreeModel)tree.getModel()).reload(twitterEntity);
  }
}
