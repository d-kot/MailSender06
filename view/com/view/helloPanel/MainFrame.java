package com.view.helloPanel;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.model.Contact;
import com.model.ContactsHolder;
import com.model.PropertiesManager;
import com.model.mailEngine.MailEngine;
import com.model.mailEngine.MyMessageHolder;
import com.view.accountPanel.AccountsPanel;
import com.view.contactsPanel.ContactPanel;
import com.view.drafts.DraftsPanel;
import com.view.inbox.InboxPanel;
import com.view.mailingPanel.MailingPanel;
import com.view.sent.SentPanel;

/**
 * 
 * @author Damian
 *	Class extending JFrame class. It is a main contener
 *for every other class.
 */
public class MainFrame extends JFrame {


	private static final long serialVersionUID = 1L;
	HelloPanel helloPanel;
	MailingPanel mailingPanel;
	ContactPanel contactPanel;
	AccountsPanel accountsPanel;
	DraftsPanel draftsPanel;
	SentPanel sentPanel;
	ContactsHolder holder = new ContactsHolder();
	static PropertiesManager currentProperties;
	static PropertiesManager generalProperties;
	InboxPanel singleInbox;
	//ArrayList<MyMessage> inboxMessages;
	static MailEngine e;
	String currPath;

	
public MainFrame(MailEngine e) 
{
	
//	generalProperties = new PropertiesManager("general",  this);
//	currentProperties = new PropertiesManager("currentProperties", this);
	currPath = MainFrame.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
	this.setDefaultSize();
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(new CardLayout(0, 0));
	this.e = e;
	this.helloPanel = new HelloPanel(this);
	this.mailingPanel = new MailingPanel(this);
	this.contactPanel = new ContactPanel(this);
	this.singleInbox = new InboxPanel(this);
	this.accountsPanel = new AccountsPanel(this);
	this.draftsPanel = new DraftsPanel(this);
	this.sentPanel = new SentPanel(this);
	
	if(holder.existsContacts())
	{
		System.out.println("laduje kontakty do listy kontaktow...");
		for(Contact c : holder.getContacts())
		{
			System.out.println(c);
			contactPanel.getDefaultListModel().addElement(c);
		}
		System.out.println("zaladowano kontakty do listy\n");
	}
	
	if(MyMessageHolder.existsFolder("inbox.ser"))
	{
		System.out.println("istnieje inbox, zatem laduje go do MyMessageList.");
		singleInbox.getMessageList().setInbox(MyMessageHolder.getInbox());
		singleInbox.getMessageList().setDlm();
	}
	else
	{
		System.out.println("nie istnieje inbox");
	}

	System.out.println("\n\n\n$$$$$$$$$$$$$$$$$$$   DRAFTS <<<< \n");
	if(MyMessageHolder.existsFolder("drafts.ser"))
	{
		System.out.println("istnieje drafts, zatem laduje go do MyMessageList.");
		draftsPanel.getDraftsList().setDrafts(MyMessageHolder.getDrafts());
		draftsPanel.getDraftsList().setDlm();
	}
	else
	{
		System.out.println("nie istnieje drafts");
	}
	System.out.println("--------- END OF DRAFTS \n\n");

	
	
	System.out.println("\n\n\n$$$$$$$$$$$$$$$$$$$   SENT <<<< \n");
	if(MyMessageHolder.existsFolder("sent.ser"))
	{
		System.out.println("istnieje sent, zatem laduje go do MyMessageList.");
		sentPanel.getSentList().setSent(MyMessageHolder.getDrafts());
		sentPanel.getSentList().setDlm();
	}
	else
	{
		System.out.println("nie istnieje sent");
	}
	System.out.println("--------- END OF sent \n\n");
	
	
	
	setVisible(true);
}

public ContactsHolder getContactsHolder() {
	return this.holder;
}

public HelloPanel getHelloPanel() {
	return helloPanel;
}

public MailingPanel getMailingPanel() {
	return mailingPanel;
}

public ContactPanel getContactPanel() {
	return contactPanel;
}



//public void setCurrentProperties(PropertiesManager man) {
//	this.currentProperties = man;
//}

//public static PropertiesManager getCurrentProperties() {
//	return currentProperties;
//}
public InboxPanel getInbox(){
	return this.singleInbox;
}

public static  MailEngine getMailEngine()
{
	return e;
}

//public static PropertiesManager getGeneralProperties()
//{
//	return generalProperties;
//}


public String getCurrentPath()
{
	 return this.currPath;
}

//public static MailEngine getMailEngine()
//{
//	return e;
//}

public DraftsPanel getDraftsPanel()
{
	return this.draftsPanel;
}


public SentPanel getSentPanel()
{
	return this.sentPanel;
}

public void setDefaultSize()
{
	this.setSize(new Dimension(650, 380));
}
}
