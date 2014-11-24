package edu.csupomona.cs356.twitter.observer;
import edu.csupomona.cs356.twitter.models.TwitterEntity;

/*
 * Observer interface!
 */
public interface TwitterObserver {
  public void update(TwitterEntity twitterEntity);
}
