package com.view.contactsPanel;

import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import com.model.Strings;
import com.model.contactsPanel.DelContactListener;
import com.model.contactsPanel.EditContactActListener;
import com.model.contactsPanel.SendEmailActionListener;
import com.model.contactsPanel.ShowContactInfoListener;

public class ContactsPopUp extends JPopupMenu {


	private static final long serialVersionUID = 1L;
	ContactPanel panel;
	JMenuItem itemSend;
	JMenuItem itemEdit;
	JMenuItem itemDelete;
	JMenuItem itemFullInfo;
	
	public ContactsPopUp(ContactPanel panel, MouseEvent e) 
	{
		this.panel = panel;
		itemSend = new JMenuItem(Strings.CPU_menuSend[Strings.i]);
		itemSend.addActionListener(new SendEmailActionListener(panel, e));
		add(itemSend);
		
		itemEdit = new JMenuItem(Strings.CPU_menuEdit[Strings.i]);
		itemEdit.addActionListener(new EditContactActListener(panel, e));
		add(itemEdit);
		
		itemFullInfo = new JMenuItem(Strings.CPU_menuInfo[Strings.i]);
		add(itemFullInfo);
		itemFullInfo.addActionListener(new ShowContactInfoListener(panel, e));
		itemDelete = new JMenuItem(Strings.CPU_menuDel[Strings.i]);
		itemDelete.addActionListener(new DelContactListener(panel, e));
		add(new JSeparator());
		add(itemDelete);
		this.show(panel.getContactsList(), e.getX(), e.getY());
		
	}
	
	
	
}
