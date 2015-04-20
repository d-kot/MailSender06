package com.view.mailingPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.model.Contact;
import com.model.mailingPanel.*;
/**
 * Class represents a small frame which appears
 * after clicking "Contacts" button in Mailing window
 * during creating a message.
 * Its main functionality is adding contacts from
 * contacts book by double clicking on them.
 * This operation is done with
 * @see AddContactToMailListener (MailingPanel)
 * @author Damian
 *
 */
public class ContactsView extends JFrame{
	

	private static final long serialVersionUID = 1L;
	MailingPanel panel;
	JPanel pane;
	JList<Contact> list;
	DefaultListModel<Contact> model;
	public ContactsView(MailingPanel panel) 
	{
		this.panel = panel;
		this.setTitle("Your contacts:");
		this.setSize(270,380);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		pane = new JPanel();
		pane.setBounds(0, 0, 270, 380);
		this.add(pane);
		pane.setLayout(null);

		model = new DefaultListModel<>();
		for(Contact c : this.panel.getWindows().getContactsHolder().getContacts())
		{
			model.addElement(c);
		}
		
		list = new JList<>(model);
		list.addMouseListener(new AddContactToMailListener(this));
		list.setFont(new Font("Tahoma", Font.BOLD, 12));
		pane.add(list);
		
		JScrollPane scrollContactsList = new JScrollPane(list);
		scrollContactsList.setBounds(5, 5, 245, 280);
		pane.add(scrollContactsList);
		
		JLabel info = new JLabel("Double tap to pick a contact");
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setVerticalAlignment(JLabel.CENTER);
		info.setBounds(5, 290, 245, 45);
		info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		info.setBackground(Color.LIGHT_GRAY);
		info.setOpaque(true);
		pane.add(info);
		
		this.add(pane);
		
		this.setVisible(true);
	}
	
	public MailingPanel getMailingPanel() {
		return this.panel;
	}
	
	public JList<Contact> getContacts() {
		return this.list;
	}
	
	public DefaultListModel<Contact> getDlmContacts() {
		return this.model;
	}
}