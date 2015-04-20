package com.view.inbox;


import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.model.mailEngine.MyMessage;
import com.view.helloPanel.MainFrame;

/**
 * This class shows default inbox window appearing 
 * after clicking a button "Inbox" in HelloPanel
 * 
 * It consists of two main elements: first is JSplitPane
 * and the other is MessageList object which extends JList
 * class. 
 * @see com.view.helloPanel.HelloPanel
 * @see InboxMessageList
 * @author Damian
 *	
 */
public class InboxPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	MainFrame window;
	JSplitPane splitPane;
	InboxMessageList messageList;
	NorthInboxPanel northPanel;
	SouthInboxPanel southPanel;
	MyMessage currentMessage = null;
	MessageContentPanel contentPanel;
	JScrollPane scrollPane;
	public InboxPanel(MainFrame frame)
	{
		this.window = frame;
		this.setLayout(new BorderLayout());

		messageList = new InboxMessageList(window, this);
		
		splitPane = new JSplitPane();
		scrollPane = new JScrollPane(messageList);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		
		splitPane.setTopComponent(scrollPane);
		
		splitPane.setBottomComponent(new JPanel());
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setDividerSize(7);
		this.add(splitPane, BorderLayout.CENTER);
		
		northPanel = new NorthInboxPanel(this);
		southPanel = new SouthInboxPanel(this);
		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
		this.window.add(this);
		this.splitPane.setDividerLocation(950);
	}
	
	public JScrollPane getInboxListScroll()
	{
		return this.scrollPane;
	}
	
	public JSplitPane getSplitPane()
	{
		return this.splitPane;
	}
	
	public InboxMessageList getMessageList()
	{
		return this.messageList;
	}
	
	public NorthInboxPanel getNorthPanel()
	{
		return this.northPanel;
	}
	
	public SouthInboxPanel getSouthPanel()
	{
		return this.southPanel;
	}
	
	public MainFrame getWindow()
	{
		return this.window;
	}
	
	public MyMessage getCurrentMessage()
	{
		return this.currentMessage;
	}
	
	public void setCurrentMessage(MyMessage m)
	{
		this.currentMessage = m;
	}
	
	public MessageContentPanel getMessageContentPanel()
	{
		return this.contentPanel;
	}
	
	public void setMessageContentPanel(MessageContentPanel panel)
	{
		this.contentPanel = panel;
	}
}



