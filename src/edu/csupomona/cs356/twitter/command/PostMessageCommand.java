package edu.csupomona.cs356.twitter.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.csupomona.cs356.twitter.gui.AdminUser;

public class PostMessageCommand implements ActionListener {
  private AdminUser ucp;

  public PostMessageCommand(AdminUser ucp) {
    this.ucp = ucp;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ucp.getUser().post(ucp.getFieldNewMessage().getText());
    ucp.getFieldNewMessage().setText("");
  }
}
