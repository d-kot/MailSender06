package com.model.contactsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.model.Contact;
import com.view.contactsPanel.ContactPanel;

public class DelContactListener implements ActionListener{

	
	ContactPanel panel;
	MouseEvent e;
	public DelContactListener(ContactPanel panel, MouseEvent e) {
		this.panel = panel;
		this.e = e;
	}
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		int index = panel.getContactsList().locationToIndex(this.e.getPoint());
		Contact c = panel.getDefaultListModel().elementAt(index);
		String dialogMessage = "Delete " + c.getName() + " " + c.getSurname() + "?";
		int n = JOptionPane.showConfirmDialog(panel, dialogMessage);
		if(n == JOptionPane.YES_OPTION) {
			panel.getWindow().getContactsHolder().removeContact(c);
			panel.getDefaultListModel().removeElement(c);
			panel.getWindow().getContactsHolder().saveContacts();
			System.out.println("deleting... " + c.getName());
		} else {
			System.out.println("operation canceled.");
		}
	}

	
}
