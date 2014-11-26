package edu.csupomona.cs356.twitter.visitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.csupomona.cs356.twitter.models.TwitterGroup;
import edu.csupomona.cs356.twitter.models.TwitterUser;

public class InvalidNameVisitor implements TwitterEntityVisitor {
  private Set<String> users = new HashSet<String>();
  private Set<String> groups = new HashSet<String>();
  private List<String> invalid = new ArrayList<String>();

  @Override
  public void visitTwitterUser(TwitterUser user) {
    if (!users.add(user.getName())) invalid.add(user.getName());
  }

  @Override
  public void visitTwitterGroup(TwitterGroup group) {
    if (!groups.add(group.getName())) invalid.add(group.getName());
  }

  public List<String> getInvalid() {
    return invalid;
  }

  public boolean checkInvalidUser(String name) {
    return nameExists(name, this.users);
  }

  public boolean checkInvalidGroup(String name) {
    return nameExists(name, this.groups);
  }
  
  private static boolean nameExists (String name, Set<String> entities) {
    if (name.indexOf(' ') >= 0) return true;
    for(String entity : entities) {
      if (entity.compareTo(name) == 0) return true;
    }
    return false;
  }
}
