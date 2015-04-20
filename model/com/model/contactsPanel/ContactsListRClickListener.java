package com.model.contactsPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import com.model.Contact;
import com.view.contactsPanel.ContactPanel;
import com.view.contactsPanel.ContactsPopUp;

public class ContactsListRClickListener implements MouseListener{

	ContactPanel panel;
	
	public ContactsListRClickListener(ContactPanel panel) {
		this.panel = panel;
	}
	
	
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			 int index = this.panel.getContactsList().locationToIndex(e.getPoint());
             System.out.println("right clicked on Item " + index);
             Contact c = panel.getDefaultListModel().elementAt(index);
             System.out.println(c);
             new ContactsPopUp(panel, e);
             
		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}

}
