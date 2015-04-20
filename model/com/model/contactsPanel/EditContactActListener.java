package com.model.contactsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import com.model.Contact;
import com.view.contactsPanel.ContactPanel;

public class EditContactActListener implements ActionListener {
	
	ContactPanel panel;
	MouseEvent e;
	
	public EditContactActListener(ContactPanel panel, MouseEvent e ) {
		this.panel = panel;
		this.e = e;
	}
	
	
	public void actionPerformed(ActionEvent e ) {
		System.out.println("editing contact...");
		this.panel.getContactsList().setEnabled(false);
		this.panel.getBtnMenu().setEnabled(false);
		int index = panel.getContactsList().locationToIndex(this.e.getPoint());
		Contact c = panel.getDefaultListModel().elementAt(index);
		panel.getTxtFieldName().setText(c.getName());
		panel.getTxtFieldEmail().setText(c.getEmail());
		panel.getTxtFieldSurname().setText(c.getSurname());
		panel.getTxtAreaVarioiusInfo().setText(c.getInfo());
		panel.getLblDodajNowyKontatk().setText("Edycja kontaktu:");
		panel.getBtnAnuluj().setVisible(true);
		panel.getBtnAdd().setText("Save");

		
		panel.getBtnAdd().addActionListener(new SaveChangesListener(panel, c));


		
		
	}
	
}
