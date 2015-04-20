package com.model.contactsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JOptionPane;

import com.model.Contact;
import com.model.Strings;
import com.view.contactsPanel.ContactPanel;

public class ContactsAddingListener implements ActionListener{

	ContactPanel panel;

	public ContactsAddingListener(ContactPanel panel) 
	{
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(panel.getBtnAdd().getText().equals(Strings.CP_btnAdd[Strings.i])) 
		{
			String name = this.panel.getTxtFieldName().getText();
			String surname = this.panel.getTxtFieldSurname().getText();
			String mail = this.panel.getTxtFieldEmail().getText();
			String info = this.panel.getTxtAreaVarioiusInfo().getText();
			
			if(name.equals("") || surname.equals("") || mail.equals("")) 
			{
				JOptionPane.showMessageDialog(panel.getWindow(), 
								Strings.CP_opt[Strings.i], 
								Strings.CP_optHed[Strings.i], 
								JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				Contact newContact = new Contact(name, surname, mail, info);
				this.panel.getWindow().getContactsHolder().addContact(newContact);
				this.panel.getWindow().getContactsHolder().saveContacts();
				this.panel.getDefaultListModel().addElement(newContact);
				this.panel.getTxtFieldName().setText("");
				this.panel.getTxtFieldSurname().setText("");
				this.panel.getTxtFieldEmail().setText("");
				this.panel.getTxtAreaVarioiusInfo().setText("");
			}
			
			Enumeration <Contact> en = this.panel.getDefaultListModel().elements();
			System.out.println(" CONTACTS IN DLM:");
			while(en.hasMoreElements()) 
			{
				Contact c = en.nextElement();
				String n = c.getName();
				String s = c.getSurname();
				String m = c.getEmail();
				String i = c.getInfo();
				System.out.println("n: " + n + "  s: " + s + "  mail:" + m + "  info: " + i);
			}
			System.out.println();
		}
	}
}
