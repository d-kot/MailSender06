package com.model.inbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

import com.model.mailEngine.MyMessageHolder;
import com.view.helloPanel.MainFrame;
import com.view.inbox.InboxPanel;

public class BtnUpdateListener implements ActionListener{
	
	InboxPanel inbox;
	
	public BtnUpdateListener(InboxPanel inbox)
	{
		this.inbox = inbox;
	}
	
	public void actionPerformed(ActionEvent e)
	{								
		SwingWorker <Void, Void> sw = new SwingWorker<Void, Void>()
		{
			@Override
			public Void doInBackground()
			{
				URL url = this.getClass().getResource("/images/load0.gif");
				inbox.getNorthPanel().getLblNE().setIcon(new ImageIcon(url));
				inbox.getNorthPanel().getLblNE().setText("Odœwie¿am...");
				
				
				/* in the MainFrame the dlm is loaded the first time! */
					MainFrame.getMailEngine().read(2);
				
		//		inbox.getMessageList().setInbox(MyMessageHolder.getInbox());
				/**
				 * 18.03 I deleted the line above and moved the 'setInbox()'
				 * method to MyMessageList contructor.
				 */
				inbox.getMessageList().updateDlm();
		
				inbox.getNorthPanel().getNIMCP().updateMessCountLbl();
				
				MyMessageHolder.save();
				
				
				inbox.getNorthPanel().getLblNE().setText("");
				inbox.getNorthPanel().getLblNE().setIcon(null);
				
				return null;
			}
		};
		sw.execute();
	}

}


