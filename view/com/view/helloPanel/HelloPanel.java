package com.view.helloPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.model.helloPanel.HelloPanelMouseListener;
import com.model.mailEngine.MailEngine;
import com.view.inbox.InboxPanel;
import com.view.mailingPanel.MailingPanel;


/**
 * This is the main menu panel of the application.
 * It contains buttons to following functions:
 * "Send E-mail"
 * @see MailingPanel
 * @see InboxPanel
 * @author Damian
 */



public class HelloPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	JFrame window;
	JButton	btnEmail,
			btnInbox,
			btnManageContacts;
	JLabel lblMailSender;
	
	public HelloPanel(MainFrame window) {
		setVisible(true);
		
		window.getContentPane().add(this, "name_3693826208873");
		setLayout(new BorderLayout());
//north
		JPanel pnlNorth = new JPanel();
		pnlNorth.setLayout(new BorderLayout());
		pnlNorth.setPreferredSize(new Dimension(350, 60));
		
//label
		lblMailSender = new JLabel("MAIL SENDER 1.0");
		lblMailSender.setFont(new Font("Serif", Font.BOLD, 20));
		lblMailSender.setHorizontalAlignment(SwingConstants.CENTER);
		lblMailSender.setOpaque(true);
		lblMailSender.setForeground(Color.white);
		lblMailSender.setBackground(Color.BLUE);
		pnlNorth.add(lblMailSender, BorderLayout.CENTER);

		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new BorderLayout());
		
		JLabel l1 = new JLabel();
		l1.setPreferredSize(new Dimension(1,1));
		pnlCenter.add(l1, BorderLayout.WEST);

		JLabel l2 = new JLabel();
		l2.setPreferredSize(new Dimension(1,1));
		pnlCenter.add(l2, BorderLayout.EAST);

		JLabel l3 = new JLabel();
		l3.setPreferredSize(new Dimension(1,1));
		pnlCenter.add(l3, BorderLayout.NORTH);

		JLabel l4 = new JLabel();
		l4.setPreferredSize(new Dimension(1,1));
		pnlCenter.add(l4, BorderLayout.SOUTH);

		
		JPanel pnlC = new JPanel();
		pnlC.setBackground(Color.LIGHT_GRAY);
		pnlC.setLayout(new GridLayout(2, 4, 10, 10));
		
		
		
//mail		
		btnEmail = new JButton();
	//	btnEmail.setText(Strings.HP_btnNewMessE[Strings.i]);
		btnEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setBounds(100, 100, 950, 600);
				window.setTitle("Send E-mail");
				setVisible(false);
				window.mailingPanel.setVisible(true);
				window.setLocationRelativeTo(null);
			}
		});
		btnEmail.addMouseListener(new HelloPanelMouseListener(btnEmail));
		addIMage(btnEmail, "Nowy E-mail", "/images/new1.png");
		pnlC.add(btnEmail);
		
//inbox
		btnInbox = new JButton();
//				btnInbox.setText(Strings.HP_btnInboxE[Strings.i]);
		btnInbox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(MailEngine.getCurrentProperties().getProperties().containsKey("account"))
				{
					window.setTitle("Inbox: " + MailEngine.getCurrentProperties().getProperty("account").toUpperCase());
				}
				setVisible(false);
				window.singleInbox.setVisible(true);
				window.setSize(950, 600);
				window.setLocationRelativeTo(null);
			}
		});
		
		
		btnInbox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		addIMage(btnInbox, "Skrzynka odbiorcza", "/images/inbox3.png");
		btnInbox.addMouseListener(new HelloPanelMouseListener(btnInbox));
		pnlC.add(btnInbox);

//sent
		JButton btnSent = new JButton();
//				btnSent.setText(Strings.HP_btnSentE[Strings.i]);
		btnSent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(MailEngine.getCurrentProperties().getProperties().containsKey("account"))
				{
					window.setTitle("Sent: " + MailEngine.getCurrentProperties().getProperty("account").toUpperCase());
				}
				setVisible(false);
				window.sentPanel.setVisible(true);
				window.setSize(new Dimension(950, 600));
				window.setLocationRelativeTo(null);
			}
		});
		
		addIMage(btnSent, "Wys³ane", "/images/sent1.png");
		btnSent.addMouseListener(new HelloPanelMouseListener(btnSent));
		pnlC.add(btnSent);
		
		
		
//drafts
		JButton btnDrafts = new JButton();
//				btnDrafts.setText(Strings.HP_btnDraftsE[Strings.i]);
		btnDrafts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnDrafts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(MailEngine.getCurrentProperties().getProperties().containsKey("account"))
				{
					window.setTitle("Drafts: " + MailEngine.getCurrentProperties().getProperty("account").toUpperCase());
				}
				setVisible(false);
				window.getDraftsPanel().setVisible(true);
				window.setSize(950, 600);
				window.setLocationRelativeTo(null);
			}
		});
		
		addIMage(btnDrafts, "Robocze", "/images/drafts1.png");
		btnDrafts.addMouseListener(new HelloPanelMouseListener(btnDrafts));
		pnlC.add(btnDrafts);

		
		
		
//contacts	
		btnManageContacts = new JButton();
//		btnManageContacts.setText(Strings.HP_btnMenContactsE[Strings.i]);
		btnManageContacts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnManageContacts.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				window.setSize(600, 450);
				window.setTitle("Manage your contact");
				setVisible(false);
				window.contactPanel.setVisible(true);
				window.setLocationRelativeTo(null);
			}
		});
		
		addIMage(btnManageContacts, "Kontakty", "/images/contacts1.png");
		btnManageContacts.addMouseListener(new HelloPanelMouseListener(btnManageContacts));
		pnlC.add(btnManageContacts);
	
//account		
		JButton btnAccount = new JButton();
//		btnAccount.setText(Strings.HP_btnYourAccountsE[Strings.i]);
		btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setTitle("Manage your account");
				setVisible(false);
				window.accountsPanel.setVisible(true);
				window.setSize(new Dimension(400,500));
				window.setLocationRelativeTo(null);
			}
		});
		
		addIMage(btnAccount, "Twoje konta", "/images/userSettings2.png");
		btnAccount.addMouseListener(new HelloPanelMouseListener(btnAccount));
		pnlC.add(btnAccount);

//about
		JButton btnAbout = new JButton();
//		btnAbout.setText(Strings.HP_btnAboutE[Strings.i]);
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAbout.setBounds(20, 304, 120, 46);
		
		addIMage(btnAbout, "Informacje", "/images/about1.png");
		btnAbout.addMouseListener(new HelloPanelMouseListener(btnAbout));
		pnlC.add(btnAbout);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 221, 339, 2);
	//	add(separator);
		
//settings		
		JButton btnSettings = new JButton();
//		btnSettings.setText(Strings.HP_btnSettingsE[Strings.i]);
		btnSettings.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		addIMage(btnSettings, "Ustawienia", "/images/settings3.png");
		btnSettings.addMouseListener(new HelloPanelMouseListener(btnSettings));
		pnlC.add(btnSettings);
	
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 291, 339, 2);
//		add(separator_1);

		pnlCenter.add(pnlC, BorderLayout.CENTER);
		
		
		this.add(pnlNorth, BorderLayout.NORTH);
		this.add(pnlCenter, BorderLayout.CENTER);
		
		
	}
	
	public void addIMage(JButton b1, String tooltip, String path)
	{
		b1.setOpaque(true);
		URL url = this.getClass().getResource(path);
		System.out.println("url tooo: " + url);
		b1.setIcon(new ImageIcon(url));
		b1.setBackground(Color.LIGHT_GRAY);
		b1.setToolTipText(tooltip);
		b1.setFocusPainted(false);
		b1.setBorder(null);
	}
}

