package com.model.contactsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.model.Contact;
import com.view.contactsPanel.ContactPanel;

class SaveChangesListener implements ActionListener {
	
	ContactPanel panel;
	Contact c;
	public SaveChangesListener(ContactPanel panel, Contact c) {
	this.panel = panel;
	this.c = c;
		
	}
	
	public void actionPerformed(ActionEvent e ) {
		if(panel.getBtnAdd().getText().equals("Save")) {
			
			String name = this.panel.getTxtFieldName().getText();
			String surname = this.panel.getTxtFieldSurname().getText();
			String mail = this.panel.getTxtFieldEmail().getText();
			String info = this.panel.getTxtAreaVarioiusInfo().getText();

			if(contactEdited(panel, c)) {
				System.out.println(">>>>>>sa zmiany");
				panel.getDefaultListModel().removeElement(c);
				if(name.equals("") || surname.equals("") || mail.equals("")) {
					System.out.println("bledne dane, sprobuj jeszcze raz...");
				} else {
					Contact newContact = new Contact(name, surname, mail, info);
					this.panel.getWindow().getContactsHolder().removeContact(c);
					this.panel.getWindow().getContactsHolder().addContact(newContact);
					this.panel.getWindow().getContactsHolder().saveContacts();
					this.panel.getDefaultListModel().addElement(newContact);
					this.panel.getTxtFieldName().setText("");
					this.panel.getTxtFieldSurname().setText("");
					this.panel.getTxtFieldEmail().setText("");
					this.panel.getTxtAreaVarioiusInfo().setText("");
					this.panel.getBtnAdd().setText("Add");
					this.panel.getContactsList().setEnabled(true);
					this.panel.getBtnAnuluj().setVisible(false);
					this.panel.getBtnMenu().setEnabled(true);
				}
			}
		}
	}
	
	
	boolean contactEdited(ContactPanel panel, Contact c) {
		
		boolean changed = false;
		String newName = panel.getTxtFieldName().getText();
		String newSur = panel.getTxtFieldSurname().getText();
		String newMail = panel.getTxtFieldEmail().getText();
		String newInfo = panel.getTxtAreaVarioiusInfo().getText();
		
		if(! (c.getName().equals(newName))) {
			changed = true;
		} else if (! (c.getSurname().equals(newSur))) {
			changed = true;
		} else if (! (c.getEmail().equals(newMail))) {
			changed = true;
		} else if (! (c.getInfo().equals(newInfo))) {
			changed = true;
		}
		
		return changed;
	}
}
