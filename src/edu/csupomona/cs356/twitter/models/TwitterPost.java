package edu.csupomona.cs356.twitter.models;

/*
 * I initially created a Hash of all the posts so I can comb through them all
 * when creating the statistics but I never used it this way. I should have
 * created the posts List in the User model.
 */
public class TwitterPost implements Comparable<TwitterPost> {
  private static Integer count = 0;
  private Integer id = ++count;
  private String message;

  /*
   * Initializer
   */
  public TwitterPost(String message) {
    this.message = message;
  }

  /*
   * Too lazy to create random strings with actual positive messages, so to
   * simulate this I'm making the character `z` a positive message. So if the
   * message contains `z` it returns true.
   */
  public boolean hasPositiveMessage() {
    return this.message.indexOf('z') >= 0;
  }

  /*
   * Used for sorting. Sorts by id.
   */
  public int compareTo(TwitterPost comparePost) {
    return this.id - comparePost.id;
  }
  
  public String toString() {
    return this.message;
  }
}
