package com.view.mailingPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import com.model.Contact;
import com.model.mailingPanel.AttachFileActionListener;
import com.model.mailingPanel.Attachment;
import com.model.mailingPanel.MailinPnlBackMenuActionListener;
import com.model.mailingPanel.SendActionListener;
import com.model.mailingPanel.ViewAttachmentsActionListener;
import com.model.mailingPanel.ViewContactsActionListener;
import com.view.helloPanel.MainFrame;

/**
 * This panel allows a user to create a new message.
 * @author Damian
 *
 */
public class MailingPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	JTextField 	txtFieldEmailAdr,
				txtFieldSubject;
	JButton		btnGoToMenu,
				btnSend,
				btnContacts,
				btnAttachFile,
				btnSeeAtt;
	JTextArea 	txtMessageArea;
	MainFrame 	window;
	ArrayList<Contact> recipients = new ArrayList<>();
	ArrayList<Attachment> attachments = new ArrayList<>();
	JLabel lblFile;
	JLabel lblFinalInfo;
	
	
	//north
	JPanel pnlNorthMain;
	JPanel pnlNorth;
	JPanel pnlNorth1;
	JPanel pnlNorth2;
	JPanel pnlNorth3;

	//center
	JPanel pnlCenter;
	
	//south
	JPanel pnlSouth;
	
	//SE
	JPanel pnlSE;
	
	/*
	 * viewAttListener object is neccessary to be instantiated as
	 * a class field because inside this object a different object
	 * namely AttachmentsView is created and then altered.
	 */
	ViewAttachmentsActionListener viewAttListener;
	
	/*
	 * swingWorker variable is used by MailingPnlBackMenuActionListener
	 * to tell if a "Menu" button should cancel sending.
	 */
	SwingWorker<Void, Void> sw;
	
	public MailingPanel(MainFrame window) 
	{
		this.window = window;
		this.setVisible(false);
		window.getContentPane().add(this);

		this.setLayout(new BorderLayout(0,1));
		setBackground(Color.blue);		

//NORTH		
		pnlNorthMain = new JPanel();
		pnlNorthMain.setBackground(Color.blue);
		pnlNorthMain.setLayout(new BorderLayout());
		
		JLabel l1 = new JLabel("");
		l1.setPreferredSize(new Dimension(10,10));
		pnlNorthMain.add(l1, BorderLayout.NORTH);
		
		pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.blue);
		pnlNorth.setLayout(new GridLayout(3, 1, 10, 5));
		
//panel 1
		
		pnlNorth1 = new JPanel();
		pnlNorth1.setBackground(Color.blue);
		pnlNorth1.setLayout(new BorderLayout());
		
		JLabel adressLabel = new JLabel("  Adres E-mail: ");
		adressLabel.setForeground(Color.WHITE);
		adressLabel.setBackground(Color.blue);
		adressLabel.setPreferredSize(new Dimension(90, 20));
		adressLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlNorth1.add(adressLabel, BorderLayout.WEST);
		
		txtFieldEmailAdr = new JTextField();
		txtFieldEmailAdr.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFieldEmailAdr.setToolTipText(
				"<html>Jesli chesz podac <b>wiecej niz jeden</b> <br> adres e-mail,"
				+ "to ka¿dy z nich oddzielaj przecinkiem.</html>");
		pnlNorth1.add(txtFieldEmailAdr, BorderLayout.CENTER);
		
		
		//panelik na kontakty i mala label
		JPanel contactsButtPanel = new JPanel();
		contactsButtPanel.setBackground(Color.blue);
		contactsButtPanel.setLayout(new BorderLayout());
		btnContacts = new JButton("Kontakty");
		btnContacts.setPreferredSize(new Dimension(80,20));
		btnContacts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnContacts.addActionListener(new ViewContactsActionListener(this));

		
		contactsButtPanel.add(btnContacts, BorderLayout.CENTER);
		JLabel ll = new JLabel("");
		ll.setPreferredSize(new Dimension(5, 20));
		JLabel ll2 = new JLabel("");
		ll2.setPreferredSize(new Dimension(10, 20));
		
		contactsButtPanel.add(ll2, BorderLayout.EAST);
		contactsButtPanel.add(ll, BorderLayout.WEST);
		pnlNorth1.add(contactsButtPanel, BorderLayout.EAST);
		
		pnlNorth.add(pnlNorth1);
		
		
// panel2
		
		pnlNorth2 = new JPanel();
		pnlNorth2.setBackground(Color.BLUE);
		pnlNorth2.setLayout(new BorderLayout());
		
		JLabel subjectLabel = new JLabel("  Temat: ");
		subjectLabel.setForeground(Color.white);
		subjectLabel.setPreferredSize(new Dimension(90, 20));

		subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlNorth2.add(subjectLabel, BorderLayout.WEST);

		txtFieldSubject = new JTextField();
		txtFieldSubject.setFont(new Font("Tahoma", Font.BOLD, 11));
		pnlNorth2.add(txtFieldSubject, BorderLayout.CENTER);
		
		JLabel l3 = new JLabel("    ");
		l3.setPreferredSize(new Dimension(95, 20));
		pnlNorth2.add(l3, BorderLayout.EAST);
		
		pnlNorth.add(pnlNorth2);
//panel 3
		
		pnlNorth3 = new JPanel();
		pnlNorth3.setBackground(Color.BLUE);
		pnlNorth3.setLayout(new BorderLayout());
		
		JLabel l6 = new JLabel("");
		l6.setBackground(Color.BLUE);
		l6.setOpaque(true);
		l6.setPreferredSize(new Dimension(90, 20));
		pnlNorth3.add(l6, BorderLayout.WEST);
		
		JLabel l7 = new JLabel("");
		l7.setBackground(Color.BLUE);
		l7.setOpaque(true);
		l7.setPreferredSize(new Dimension(95, 20));
		pnlNorth3.add(l7, BorderLayout.EAST);

		JPanel pnlNorth3Center = new JPanel();
		pnlNorth3Center.setLayout(new GridLayout(1, 2));
		pnlNorth3Center.setBackground(Color.blue);
		
		
		JPanel pnlN3a = new JPanel();
		pnlN3a.setLayout(new GridLayout(1, 3));
		pnlN3a.setBackground(Color.blue);
		btnAttachFile = new JButton("Dodaj plik");
		btnAttachFile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAttachFile.setPreferredSize(new Dimension(70, 20));
		
		btnAttachFile.addActionListener(new AttachFileActionListener(this));

		
		pnlN3a.add(btnAttachFile);
		pnlN3a.add(new JLabel(""));
		pnlN3a.add(new JLabel(""));
		pnlNorth3Center.add(pnlN3a);
		
		btnSeeAtt = new JButton("Do³¹czone pliki: ");
		btnSeeAtt.setVisible(false);
		btnSeeAtt.setOpaque(true);
		btnSeeAtt.setBackground(Color.BLUE);
		btnSeeAtt.setForeground(Color.white);
		
		viewAttListener = new ViewAttachmentsActionListener(this);
		btnSeeAtt.addActionListener(viewAttListener);

		
		btnSeeAtt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		pnlNorth3Center.add(btnSeeAtt);
		
		pnlNorth3.add(pnlNorth3Center, BorderLayout.CENTER);
		
		pnlNorth.add(pnlNorth3);
		
		pnlNorthMain.add(pnlNorth, BorderLayout.CENTER);
		add(pnlNorthMain, BorderLayout.NORTH);
		
		
//center		
		txtMessageArea = new JTextArea();
		txtMessageArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMessageArea.setLineWrap(true);
		txtMessageArea.setWrapStyleWord(true);
		
		
		
		pnlCenter = new JPanel();
		pnlCenter.setLayout(new BorderLayout());
		JScrollPane pane = new JScrollPane(txtMessageArea);
		pnlCenter.add(pane, BorderLayout.CENTER);
		
		add(pnlCenter, BorderLayout.CENTER);
		
		JLabel l4 = new JLabel("");
		l4.setOpaque(true);
		l4.setBackground(Color.BLUE);
		l4.setPreferredSize(new Dimension(5, 5));
		JLabel l5 = new JLabel("");
		l5.setOpaque(true);
		l5.setBackground(Color.BLUE);
		l5.setPreferredSize(new Dimension(5, 5));
		
		
//SOUTH		
		pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.DARK_GRAY);
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.setPreferredSize(new Dimension(950, 50));
//SE		
		pnlSE = new JPanel();
		pnlSE.setOpaque(false);
		pnlSE.setLayout(new BorderLayout());
		JLabel l12 = new JLabel("");
		l12.setPreferredSize(new Dimension(10,15));
		pnlSE.add(l12, BorderLayout.NORTH);
		JLabel l13 = new JLabel("");
		l13.setPreferredSize(new Dimension(10,10));
		pnlSE.add(l13, BorderLayout.SOUTH);
		
		JLabel l14 = new JLabel("");
		l14.setPreferredSize(new Dimension(10,10));
		pnlSE.add(l14, BorderLayout.EAST);

		JLabel l15 = new JLabel("");
		l15.setPreferredSize(new Dimension(10,10));
		pnlSE.add(l15, BorderLayout.WEST);
		
		btnSend = new JButton("Wyœlij");
		btnSend.setIcon(new ImageIcon("/images/load.gif"));

		btnSend.setPreferredSize(new Dimension(80,20));
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnSend.addActionListener(new SendActionListener(this));
		
		pnlSE.add(btnSend, BorderLayout.CENTER);
		
//SW		
		JPanel pnlSW = new JPanel();
		pnlSW.setOpaque(false);
		pnlSW.setLayout(new BorderLayout());
		JLabel l16 = new JLabel("");
		l16.setPreferredSize(new Dimension(10,15));
		pnlSW.add(l16, BorderLayout.NORTH);
		JLabel l17 = new JLabel("");
		l17.setPreferredSize(new Dimension(10,10));
		pnlSW.add(l17, BorderLayout.SOUTH);
		
		JLabel l18 = new JLabel("");
		l18.setPreferredSize(new Dimension(10,10));
		pnlSW.add(l18, BorderLayout.EAST);

		JLabel l19 = new JLabel("");
		l19.setPreferredSize(new Dimension(10,10));
		pnlSW.add(l19, BorderLayout.WEST);
		
		btnGoToMenu = new JButton("Menu");
		btnGoToMenu.setPreferredSize(new Dimension(80,20));
		btnGoToMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnGoToMenu.addActionListener(new MailinPnlBackMenuActionListener(this));
		
		pnlSW.add(btnGoToMenu, BorderLayout.CENTER);

		
		//SC
		JPanel pnlC = new JPanel();
		pnlC.setOpaque(false);
		pnlC.setLayout(new BorderLayout());

		lblFinalInfo = new JLabel();
		lblFinalInfo.setForeground(Color.white);
		lblFinalInfo.setVerticalAlignment(SwingConstants.CENTER);
		lblFinalInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalInfo.setOpaque(true);
		lblFinalInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFinalInfo.setBackground(Color.DARK_GRAY);
		pnlC.add(lblFinalInfo, BorderLayout.CENTER);

		pnlSouth.add(pnlSW, BorderLayout.WEST);
		pnlSouth.add(pnlSE, BorderLayout.EAST);
		pnlSouth.add(pnlC, BorderLayout.CENTER);
		
		add(pnlSouth, BorderLayout.SOUTH);
		
	}
	
	public ArrayList<Contact> getRecipients() 
	{
		return recipients;
	}
	
	public void addRecipient(Contact c) 
	{
		recipients.add(c);
	}
	
	public ArrayList<Attachment> getAttachments() 
	{
		return attachments;
	}
	
	public void addAttachment(Attachment a) 
	{
		attachments.add(a);
	}
	
	public void removeAttachment(Attachment a)
	{
		attachments.remove(a);
	}
	
	public void clearAttachments()
	{
		attachments = new ArrayList<Attachment>();
		
	}

	public JTextField getTxtFieldEmailAdr() 
	{
		return txtFieldEmailAdr;
	}

	public JTextField getTxtFieldSubject() 
	{
		return txtFieldSubject;
	}

	public JButton getBtnGoToMenu() 
	{
		return btnGoToMenu;
	}

	public JButton getBtnSend() 
	{
		return btnSend;
	}

	public JButton getBtnContacts()
	{
		return btnContacts;
	}

	public JButton getBtnAttachFile() 
	{
		return btnAttachFile;
	}

	public JTextArea getTxtMessageArea() 
	{
		return txtMessageArea;
	}
	
	public MainFrame getWindows() 
	{
		return this.window;
	}
	
	public JButton getBtnSeeAtt() 
	{
		return this.btnSeeAtt;
	}
	
	public void emptyMailingPanel()
	{
		this.txtFieldEmailAdr.setText("");
		this.txtFieldSubject.setText("");
		this.txtMessageArea.setText("");
		this.lblFinalInfo.setText("");
		this.getBtnSeeAtt().setVisible(false);
//		this.getViewAttListener().getAttachmentsView().removeAll();
		this.clearAttachments();
	}
	
	public JLabel getlblSendInfo()
	{
		return this.lblFinalInfo;
	}
	
	public JPanel getPnlSE()
	{
		return pnlSE;
	}
	
	public ViewAttachmentsActionListener getViewAttListener()
	{
		return this.viewAttListener;
	}
	
	public SwingWorker<Void, Void> getSW()
	{
		return this.sw;
	}
	
	public void setSW(SwingWorker<Void, Void> sw)
	{
		this.sw = sw;
	}
}
