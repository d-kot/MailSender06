package com.model.contactsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.model.Contact;
import com.view.contactsPanel.ContactPanel;

public class ShowContactInfoListener implements ActionListener {

	ContactPanel panel;
	MouseEvent e;
	
	public ShowContactInfoListener(ContactPanel panel, MouseEvent e) {
		this.panel = panel;
		this.e = e;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("showing full info of...");
		
		int index = panel.getContactsList().locationToIndex(this.e.getPoint());
		Contact c = panel.getDefaultListModel().elementAt(index);
		System.out.println("INFO:" + c.getInfo());
		JOptionPane.showMessageDialog(panel, composeMessage(c));
	}
	
	String composeMessage(Contact c) {
		String message = 
			"<html>" + 
					"NAME:"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<font color=blue>" + c.getName() +"</font><br>" +  
					"SURNAME:"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<font color=blue>" + c.getSurname() + "</font><br>" +
					"EMAIL:"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<font color=blue>" + c.getEmail() + "</font><br" +
					"<br>FULL INFO:<br>"
					+ "<font color=blue>" +	c.getInfo() + "</font><br>" +
			"</html>";
		
		return message;
	}

	
}
