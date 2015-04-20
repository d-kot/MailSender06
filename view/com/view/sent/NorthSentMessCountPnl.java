package com.view.sent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.mailEngine.MyMessageHolder;
import com.view.drafts.DraftsPanel;

public class NorthSentMessCountPnl extends JPanel{
	JLabel messageCount;
	SentPanel pnlSent;
	
	public NorthSentMessCountPnl(SentPanel pnlSent)
	{
		this.pnlSent = pnlSent;
		
		this.setOpaque(true);
		this.setBackground(Color.BLUE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		int numOfDrafts = MyMessageHolder.getSent().size();
		
		messageCount = new JLabel("     Sent ("+ numOfDrafts + ")");
		messageCount.setPreferredSize(new Dimension(250, 25));
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
		int readMess = MyMessageHolder.getSent().size();
		messageCount.setText("      Sent (" + readMess+ ")");
	}

}
