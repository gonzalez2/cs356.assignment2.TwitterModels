package edu.csupomona.cs356.twitter.observer;
import edu.csupomona.cs356.twitter.models.TwitterEntity;

public interface Observer {
  public void update(TwitterEntity twitterEntity);
}
