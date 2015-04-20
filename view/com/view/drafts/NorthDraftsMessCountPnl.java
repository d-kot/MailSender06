package com.view.drafts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.mailEngine.MyMessageHolder;

public class NorthDraftsMessCountPnl extends JPanel {

	JLabel messageCount;
	DraftsPanel draftsPnl;
	
	public NorthDraftsMessCountPnl(DraftsPanel draftsPnl)
	{
		this.draftsPnl = draftsPnl;
		
		this.setOpaque(true);
//		this.setBackground(Color.GRAY);
		this.setBackground(Color.BLUE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		int numOfDrafts = MyMessageHolder.getDrafts().size();
		
		messageCount = new JLabel("     Drafts ("+ numOfDrafts + ")");
		messageCount.setPreferredSize(new Dimension(250, 25));
//		messageCount = new JLabel(Strings.NIMCP_lblMessCount[Strings.i]+ "(" + unreadMes + "/" + readMess + ")");
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
//		int unreadMes = MyMessageHolder.getUnread();
		int readMess = MyMessageHolder.getDrafts().size();
		messageCount.setText("Drafts (" + readMess+ ")");
	}
}
