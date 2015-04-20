package com.view.sent;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.model.MessageOut;
import com.view.helloPanel.MainFrame;

public class SentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame window;
	SentMessageList sentList;
	JSplitPane splitPane;
	JScrollPane scrollPane;
	NorthSentPanel pnlNorthSent;
	SentContentPanel sentContentPnl;
	
	MessageOut currentSent;
	
	public SentPanel(MainFrame window)
	{
		this.window = window;
		
		this.setLayout(new BorderLayout());

		sentList = new SentMessageList(window, this);
		
		splitPane = new JSplitPane();
		scrollPane = new JScrollPane(sentList);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		splitPane.setTopComponent(scrollPane);
		
		splitPane.setBottomComponent(new JPanel());
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setDividerSize(7);
		this.add(splitPane, BorderLayout.CENTER);
		
		pnlNorthSent = new NorthSentPanel(this);
		this.add(pnlNorthSent, BorderLayout.NORTH);
		this.window.add(this);
		this.splitPane.setDividerLocation(950);

	}
	public MainFrame getWindow() 
	{
		return window;
	}
	
	public void setWindow(MainFrame window) 
	{
		this.window = window;
	}
	
	public SentMessageList getSentList() 
	{
		return sentList;
	}
	
	public void setSentList(SentMessageList sentList) 
	{
		this.sentList = sentList;
	}
	
	public JSplitPane getSplitPane() 
	{
		return splitPane;
	}
	
	public void setSplitPane(JSplitPane splitPane) 
	{
		this.splitPane = splitPane;
	}
	
	public JScrollPane getScrollPane() 
	{
		return scrollPane;
	}
	
	public void setScrollPane(JScrollPane scrollPane) 
	{
		this.scrollPane = scrollPane;
	}
	
	public NorthSentPanel getPnlNorthSent() 
	{
		return pnlNorthSent;
	}
	
	public void setPnlNorthSent(NorthSentPanel pnlNorthSent)
	{
		this.pnlNorthSent= pnlNorthSent;
	}
	
	public void setCurrentSent(MessageOut mOut)
	{
		this.currentSent = mOut;
	}
	
	public MessageOut getCurrentSent()
	{
		return this.currentSent;
	}
	
	
	public SentContentPanel getSentContentPnl() 
	{
		return sentContentPnl;
	}
	
	public void setSentContentPnl(SentContentPanel sentContentPnl) 
	{
		this.sentContentPnl = sentContentPnl;
	}
	
	

	
	
}
