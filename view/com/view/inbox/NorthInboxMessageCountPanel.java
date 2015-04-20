package com.view.inbox;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Strings;
import com.model.mailEngine.MyMessageHolder;

/**
 * This panel contains one major element 'MessageCount' label.
 * This label shows up in the upper left corner of the 'NorthInboxPanel'
 * of the 'InboxPanel' and presents how many messages are read and unread.
 * 
 * @see InboxPanel
 * @see NorthInboxPanel
 * @see NorthCenterInboxPanel
 * @author Damian
 */
public class NorthInboxMessageCountPanel extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	
	InboxPanel panel;
	JLabel messageCount;
	public NorthInboxMessageCountPanel(InboxPanel inbox)
	{
		this.panel = inbox;
		this.setOpaque(true);
		this.setBackground(Color.BLUE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		int unreadMes = MyMessageHolder.getUnread();
		int readMess = MyMessageHolder.getInbox().size();
		
		
		messageCount = new JLabel(Strings.NIMCP_lblMessCount[Strings.i]+ "(" + unreadMes + "/" + readMess + ")");
		messageCount.setForeground(Color.WHITE);
		messageCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(messageCount);
	}
	
	public JLabel getLblMessageCount()
	{
		return this.messageCount;
	}
	
	public void updateMessCountLbl()
	{
		int unreadMes = MyMessageHolder.getUnread();
		int readMess = MyMessageHolder.getInbox().size();
		messageCount.setText(Strings.NIMCP_lblMessCount[Strings.i] + "(" + unreadMes + "/" + readMess + ")");
	}
}