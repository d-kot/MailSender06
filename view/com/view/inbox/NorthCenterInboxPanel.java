package com.view.inbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.model.Strings;
import com.model.inbox.BtnUpdateListener;
import com.model.mailEngine.MailEngine;
import com.model.mailEngine.MyMessageHolder;


/**
 * This panel makes the center part of the 'NorthInboxPanel' of
 * the 'InboxPanel' class. It contains four buttons.
 * 
 * @author Damian
 * @see NorthInboxMessageCountPanel
 * @see InboxPanel
 * @see NorthInboxPanel
 */
public class NorthCenterInboxPanel extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	
	InboxPanel inbox;
	JButton btnMessListOnly;
	JButton btnNewEmail;
	JButton btnUpdateNow;
	JButton btnMainMenu;
	
	public NorthCenterInboxPanel(InboxPanel inbox)
	{
		this.inbox = inbox;
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(true);
		this.setBackground(Color.BLUE);
		
		btnMainMenu = new JButton(Strings.NCIP_btnMenu[Strings.i]);
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				inbox.setVisible(false);
				inbox.getWindow().setDefaultSize();
				inbox.getWindow().getHelloPanel().setVisible(true);
				inbox.getWindow().setLocationRelativeTo(null);
			}
		});
		this.add(btnMainMenu);
		
		btnNewEmail = new JButton(Strings.NCIP_btnNewMail[Strings.i]);;
		btnNewEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		//btnNewEmail.setPreferredSize(new Dimension(150, 25));
		btnNewEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				inbox.setVisible(false);
				inbox.getWindow().getMailingPanel().setVisible(true);
			}
		});
		this.add(btnNewEmail);
														/* UPDATE BUTTON*/
		btnUpdateNow = new JButton(Strings.NCIP_btnUpdate[Strings.i]);
		btnUpdateNow.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnUpdateNow.addActionListener(new BtnUpdateListener(inbox));
		
		this.add(btnUpdateNow);
			
		btnMessListOnly = new JButton(Strings.NCIP_btnInboxOnly[Strings.i]);
		btnMessListOnly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMessListOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				inbox.getSplitPane().setDividerLocation(700);
				if(inbox.getMessageContentPanel() != null)
				{
				inbox.getMessageContentPanel().setVisible(false);
				inbox.getSouthPanel().setVisible(false);
				}
			}
		});
		this.add(btnMessListOnly);
	}
	
	
	public JButton getBtnNewEmail()
	{
		return this.btnNewEmail;
	}
}
