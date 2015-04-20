package com.view.drafts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.model.Strings;

public class NorthDraftsPanel extends JPanel {

	DraftsPanel draftsPnl;
	NorthDraftsMessCountPnl pnlCountDrafts;
	NorthCenterDraftsPnl pnlCenterNorrthDrafts;
	NorthWestDraftsPnl pnlNorthWestDrafts;
	
	public NorthDraftsPanel(DraftsPanel draftsPnl)
	{
	
	this.draftsPnl = draftsPnl;
	this.setOpaque(true);
	setBackground(Color.BLUE);
	this.setLayout(new BorderLayout());
	pnlCountDrafts = new NorthDraftsMessCountPnl(draftsPnl);
	this.add(pnlCountDrafts, BorderLayout.WEST);
	
	pnlCenterNorrthDrafts = new NorthCenterDraftsPnl(draftsPnl);
	add(pnlCenterNorrthDrafts, BorderLayout.CENTER);
	
	pnlNorthWestDrafts = new NorthWestDraftsPnl(draftsPnl);
	add(pnlNorthWestDrafts, BorderLayout.EAST);
	
//	this.add(
//	new JLabel("                                                      "),
//	BorderLayout.EAST);
	
	}
	
	public NorthDraftsMessCountPnl getNDMCP()
	{
		return this.pnlCountDrafts;
	}
	
	public NorthWestDraftsPnl getNorthWestDraftsPnl()
	{
		return this.pnlNorthWestDrafts;
	}
	
}

