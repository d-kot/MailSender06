package com.model.inbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.model.mailEngine.MyMessage;
import com.model.mailEngine.MyMessageHolder;
import com.view.inbox.InboxPanel;

public class ConfirmMessDelListener implements ActionListener{

	
	InboxPanel inboxPanel;
	
	public ConfirmMessDelListener(InboxPanel panel)
	{
		this.inboxPanel = panel;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("before: ");
		MyMessageHolder.show();

		MyMessage m = inboxPanel.getCurrentMessage();
		System.out.println(m.getSendDate() + "  " + m.getSender() + " << to be deleted");
		MyMessageHolder.removeMyMessage(m);
		System.out.println("\nafter: ");
		MyMessageHolder.show();
		inboxPanel.getMessageList().setInbox(MyMessageHolder.getInbox());
		inboxPanel.getMessageList().updateDlm();
		inboxPanel.getMessageContentPanel().getMessageContentArea().setText("");
		
		MyMessageHolder.save();
		
		inboxPanel.getSplitPane().setDividerLocation(700);
		inboxPanel.getSouthPanel().getConfirmDelPnl().setVisible(false);
		inboxPanel.getSouthPanel().getBtnDel().setVisible(true);
		inboxPanel.getSouthPanel().getBtnReply().setVisible(true);
		inboxPanel.getSouthPanel().setVisible(false);
		
		inboxPanel.getNorthPanel().getNIMCP().updateMessCountLbl();
	}
}
