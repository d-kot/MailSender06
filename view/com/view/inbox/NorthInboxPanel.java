package com.view.inbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class provides blue panel in the upper part
 * of the "InboxPanel". It conatins two subPanels:
 *  'NorthInboxMessageCountPanel' which contains only a label
 *  showing how many messages are unread and read.
 *  The other panel is 'NorthCenterInboxPanel' that contains 
 *  four buttons.
 *  
 *  The reason for creating 'NorthInboxPanel' in such a way is
 *  because I needed an pliable and resizable panel. So I needed
 *  to use Layout Managers. Decoupling one panel into smaller ones
 *  let me create a fairly well looking solution.
 * 
 * @see InboxPanel
 * @see SouthInboxPanel
 * @see NorthInboxMessageCountPanel
 * @see NorthCenterInboxPanel
 * @author Damian
 *
 */

public class NorthInboxPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	InboxPanel inbox;
	NorthInboxMessageCountPanel countPanel;
	NorthCenterInboxPanel northCenterInbox;
	JLabel lblNE;
	public NorthInboxPanel(InboxPanel inbox)
	{
		this.inbox = inbox;
		this.setOpaque(true);
		setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		
		countPanel = new NorthInboxMessageCountPanel(inbox);
		this.add(countPanel, BorderLayout.WEST);
		
		northCenterInbox = new NorthCenterInboxPanel(inbox);
		this.add(northCenterInbox, BorderLayout.CENTER);
		
		lblNE = new JLabel();
		lblNE.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNE.setForeground(Color.white);
		lblNE.setPreferredSize(new Dimension(270, 35));
		
		
		this.add(
		lblNE,
		BorderLayout.EAST);
	}
	
	public NorthInboxMessageCountPanel getNIMCP()
	{
		return this.countPanel;
	}
	
	public NorthInboxMessageCountPanel getNorthInboxMessageCountPanel()
	{
		return this.countPanel;
	}
	
	public NorthCenterInboxPanel getNorthCenterInboxPanel()
	{
		return this.northCenterInbox;
	}
	
	public JLabel getLblNE()
	{
		return this.lblNE;
	}
}
