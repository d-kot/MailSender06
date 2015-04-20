package com.view.drafts;

import java.awt.Font;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import com.model.MessageOut;
import com.model.drafts.DraftDoubleClickedListener;
import com.model.mailEngine.MyMessageHolder;
import com.view.helloPanel.MainFrame;

public class DraftsMessageList extends JList<MessageOut>{

	MainFrame window;
	DraftsPanel pnlDrafts;
	TreeMap<Long, MessageOut> drafts = null;
	DefaultListModel<MessageOut> dlm;

	
	public DraftsMessageList(MainFrame window, DraftsPanel pnlDrafts)
	{
		this.window = window;
		this.pnlDrafts = pnlDrafts;
		
		dlm = new DefaultListModel<>();
		this.setModel(dlm);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setFixedCellWidth(895);
		setOpaque(true);
		setBounds(10, 10, 915, 500);
		this.setDrafts(MyMessageHolder.getDrafts());
		this.addMouseListener(new DraftDoubleClickedListener(pnlDrafts));

		
	}

	public DefaultListModel<MessageOut> getDLM()
	{
		return this.dlm;
	}
	
	public void setDrafts(TreeMap<Long, MessageOut> in)
	{
		System.out.println("inside setDrafts method.");
		this.drafts = new TreeMap<>(in);
		System.out.println("drafts set.");
	}
	
	
	public void addMessage(MessageOut m)
	{
		this.dlm.addElement(m);
	}
	
	public void setDlm()
	{
		ArrayList<Long> keys = new ArrayList<>(MyMessageHolder.getDrafts().keySet());
		
		for(int i = (keys.size()-1); i >=0; i--)
		{
			this.dlm.addElement(MyMessageHolder.getDrafts().get(keys.get(i)));
		}
	}

	
	public void updateDlm()
	{
		ArrayList<Long> keys = new ArrayList<>(MyMessageHolder.getDrafts().keySet());
		
		this.dlm.removeAllElements();
		
		for(int i = (keys.size()-1); i >=0; i--)
		{
			this.dlm.addElement(MyMessageHolder.getDrafts().get(keys.get(i)));
		}
	}
	
}
