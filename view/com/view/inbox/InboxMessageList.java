package com.view.inbox;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import com.model.inbox.MessageDoubleClickedListener;
import com.model.mailEngine.MyMessage;
import com.model.mailEngine.MyMessageHolder;
import com.view.helloPanel.MainFrame;



/**
* This class extends JList and it is the upper part of
* JSplitPane of the SingleMessageInbox class. It presents
* messages in a neat way through JList interface.
* @see InboxPanel
* @see MessageDoubleClickedListener
* @author Damian
 *
 */
public class InboxMessageList extends JList<MyMessage>
{
	private static final long serialVersionUID = 1L;
	TreeMap<Long, MyMessage> inbox = null;
	MainFrame window;
	DefaultListModel<MyMessage> dlm;
	InboxPanel singleMessInbox;
	public InboxMessageList(MainFrame window, InboxPanel sInbox)
	{
		this.singleMessInbox = sInbox;
		dlm = new DefaultListModel<>();
		this.setModel(dlm);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.window = window;
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setFixedCellWidth(895);
		setOpaque(true);
		setBounds(10, 10, 915, 500);
		this.setInbox(MyMessageHolder.getInbox());
		this.addMouseListener(new MessageDoubleClickedListener(singleMessInbox));
	}
	

	public void addMessage(MyMessage m)
	{
		this.dlm.addElement(m);
	}
	
	public void setDlm()
	{
		ArrayList<Long> keys = new ArrayList<>(MyMessageHolder.getInbox().keySet());
		
		for(int i = (keys.size()-1); i >=0; i--)
		{
			this.dlm.addElement(MyMessageHolder.getInbox().get(keys.get(i)));
		}
	}

	
	public void updateDlm()
	{
		ArrayList<Long> keys = new ArrayList<>(MyMessageHolder.getInbox().keySet());
		
		this.dlm.removeAllElements();
		
		for(int i = (keys.size()-1); i >=0; i--)
		{
			this.dlm.addElement(MyMessageHolder.getInbox().get(keys.get(i)));
		}
	}
	
	public DefaultListModel<?> getDLM()
	{
		return this.dlm;
	}
	
	public TreeMap<Long, MyMessage>getInbox()
	{
		return this.inbox;
	}
	
	public void setInbox(TreeMap<Long, MyMessage> in)
	{
		System.out.println("inside setInbox method.");
		this.inbox = new TreeMap<>(in);
		System.out.println("inbox set.");
	}
}
