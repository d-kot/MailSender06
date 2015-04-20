package com.view.sent;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.view.drafts.NorthCenterDraftsPnl;
import com.view.drafts.NorthDraftsMessCountPnl;
import com.view.drafts.NorthWestDraftsPnl;

public class NorthSentPanel extends JPanel{
	SentPanel sentPanel;
	NorthSentMessCountPnl pnlCountSent;
	NorthCenterSentPnl pnlCenterNorrthSent;
	NorthWestSentPnl pnlNorthWestSent;
	
	public NorthSentPanel(SentPanel sentPanel)
	{
	
	this.sentPanel = sentPanel;
	this.setOpaque(true);
	setBackground(Color.BLUE);
	this.setLayout(new BorderLayout());
	pnlCountSent = new NorthSentMessCountPnl(sentPanel);
	this.add(pnlCountSent, BorderLayout.WEST);
	
	pnlCenterNorrthSent = new NorthCenterSentPnl(sentPanel);
	add(pnlCenterNorrthSent, BorderLayout.CENTER);
	
	pnlNorthWestSent = new NorthWestSentPnl(sentPanel);
	add(pnlNorthWestSent, BorderLayout.EAST);
	
	}
	
	public NorthSentMessCountPnl getNSMCP()
	{
		return this.pnlCountSent;
	}
	
	public NorthWestSentPnl getNorthWestSentPnl()
	{
		return this.pnlNorthWestSent;
	}
	
	public NorthCenterSentPnl getNorthCenterSentPnl(){
		return this.pnlCenterNorrthSent;
	}

}
