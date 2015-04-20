package com.model.contactsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import com.model.Contact;
import com.view.contactsPanel.ContactPanel;

public class SendEmailActionListener implements ActionListener{

	ContactPanel panel;
	MouseEvent e;
	
	public SendEmailActionListener(ContactPanel panel, MouseEvent e ) {
		this.panel = panel;
		this.e = e;
	}
	
	
	public void actionPerformed(ActionEvent e ) {
		System.out.println("sending mail...");
		panel.getWindow().setBounds(100, 100, 950, 600);

		int index = panel.getContactsList().locationToIndex(this.e.getPoint());
		Contact c = panel.getDefaultListModel().elementAt(index);
		panel.setVisible(false);
		panel.getWindow().getMailingPanel().setVisible(true);
		panel.getWindow().getMailingPanel().getTxtFieldEmailAdr().setText(c.getEmail());
		panel.getWindow().getMailingPanel().addRecipient(c);
	}

	
}
