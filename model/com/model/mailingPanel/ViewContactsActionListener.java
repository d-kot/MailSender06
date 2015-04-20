package com.model.mailingPanel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.model.Strings;
import com.view.mailingPanel.ContactsView;
import com.view.mailingPanel.MailingPanel;

public class ViewContactsActionListener implements ActionListener{
	
	MailingPanel panel;
	
	public ViewContactsActionListener(MailingPanel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e ) {
		if(panel.getWindows().getContactsHolder().existsContacts()) {
		ContactsView internalF = new ContactsView(panel);
		internalF.setLocationRelativeTo(panel);
		} else {
			JOptionPane.showMessageDialog(panel, Strings.VCAL_infoMes[Strings.i], 
										Strings.VCAL_infoH[Strings.i], 
										JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}

