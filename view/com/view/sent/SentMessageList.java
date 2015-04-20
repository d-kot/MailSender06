package com.view.sent;

import java.awt.Font;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import com.model.MessageOut;
import com.model.mailEngine.MyMessageHolder;
import com.model.sent.SentDoubleClickedListener;
import com.view.helloPanel.MainFrame;

public class SentMessageList extends JList<MessageOut>{
	MainFrame window;
	SentPanel pnlSent;
	TreeMap<Long, MessageOut> sent = null;
	DefaultListModel<MessageOut> dlm;

	
	public SentMessageList(MainFrame window, SentPanel pnlSent)
	{
		this.window = window;
		this.pnlSent = pnlSent;
		
		dlm = new DefaultListModel<>();
		this.setModel(dlm);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setFixedCellWidth(895);
		setOpaque(true);
		setBounds(10, 10, 915, 500);
		this.setSent(MyMessageHolder.getSent());
		this.addMouseListener(new SentDoubleClickedListener(pnlSent));

		
	}

	public DefaultListModel<MessageOut> getDLM()
	{
		return this.dlm;
	}
	
	public void setSent(TreeMap<Long, MessageOut> in)
	{
		System.out.println("inside set sent method.");
		this.sent = new TreeMap<>(in);
		System.out.println("sent set.");
	}
	
	
	public void addMessage(MessageOut m)
	{
		this.dlm.addElement(m);
	}
	
	public void setDlm()
	{
		ArrayList<Long> keys = new ArrayList<>(MyMessageHolder.getSent().keySet());
		
		for(int i = (keys.size()-1); i >=0; i--)
		{
			this.dlm.addElement(MyMessageHolder.getSent().get(keys.get(i)));
		}
	}

	
	public void updateDlm()
	{
		ArrayList<Long> keys = new ArrayList<>(MyMessageHolder.getSent().keySet());
		
		this.dlm.removeAllElements();
		
		for(int i = (keys.size()-1); i >=0; i--)
		{
			this.dlm.addElement(MyMessageHolder.getSent().get(keys.get(i)));
		}
	}

}
