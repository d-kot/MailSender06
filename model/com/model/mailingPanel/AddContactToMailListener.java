package com.model.mailingPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.model.Contact;
import com.view.mailingPanel.ContactsView;

public class AddContactToMailListener implements MouseListener {

	ContactsView view;
	
	public AddContactToMailListener(ContactsView contactsView) {
		this.view = contactsView;
	}
	
	public void mouseClicked(MouseEvent e)
	{	
		if(e.getClickCount() == 2)
		{
			System.out.println("double click!");
			int index = view.getContacts().locationToIndex(e.getPoint());
			Contact c = view.getDlmContacts().elementAt(index);
			view.getMailingPanel().addRecipient(c);
			/*
			 * dodajac wielu adresatow musze pamietac, by odpowiednio to przedstawic
			 */
			String currRecipient = view.getMailingPanel().
									getTxtFieldEmailAdr().getText();
			if(currRecipient.equals(""))
			{
				view.getMailingPanel().getTxtFieldEmailAdr().
				setText(c.getEmail());				
			} else
				{
					view.getMailingPanel().getTxtFieldEmailAdr().
					setText(currRecipient + ", " + c.getEmail());
				}
			
			view.setVisible(false);
			
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
	
	
	
	
}
