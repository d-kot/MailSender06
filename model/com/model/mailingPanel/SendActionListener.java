package com.model.mailingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

import com.model.MessageOut;
import com.model.mailEngine.MailEngine;
import com.model.mailEngine.MyMessageHolder;
import com.view.helloPanel.MainFrame;
import com.view.mailingPanel.MailingPanel;

/**
 * This class provides functionality to 'Send' button in
 * MailingPanel. This class uses javax.swing.swing.SwingWorker
 * class which moves sending process to a new separate thread.
 * @author Damian
 *
 */
public class SendActionListener implements ActionListener {

	MailingPanel mPanel;
	SwingWorker<Void, Void> sw;
	
	public SendActionListener(MailingPanel mPanel)
	{
		this.mPanel = mPanel;
		
	}
	
		public void actionPerformed(ActionEvent e) 
		{

			sw = new SwingWorker<Void, Void>()
			{
				@Override
				public Void doInBackground()
				{
					mPanel.setSW(sw);
					
					URL url = this.getClass().getResource("/images/load0.gif");
					ImageIcon i = new ImageIcon(url);
					mPanel.getlblSendInfo().setIcon(i);
					mPanel.getlblSendInfo().setText("Wysy³anie...");
					String messageText = mPanel.getTxtMessageArea().getText();
					String subject = mPanel.getTxtFieldSubject().getText();
					String addr = mPanel.getTxtFieldEmailAdr().getText();
					
					if(mPanel.getAttachments().size() == 0)
					{	
						MainFrame.getMailEngine()
						.sendMail(MailEngine.getCurrentProperties().getProperty("mail"), addr, subject, messageText);
					}
					else
					{
						MainFrame.getMailEngine()
						.sendMail(MailEngine.getCurrentProperties().getProperty("mail"), addr, subject, messageText, mPanel.getAttachments());					
		
					}
					System.out.println("SUCCESS");
					
					MessageOut mOut = new MessageOut(addr, subject, messageText);
					MyMessageHolder.addSent(mOut);
					MyMessageHolder.saveSent();
					mPanel.getWindows().getSentPanel().getSentList().updateDlm();
					mPanel.getWindows().getSentPanel().getPnlNorthSent().getNSMCP().updateMessCountLbl();
					mPanel.emptyMailingPanel();
					
					mPanel.getlblSendInfo().setIcon(null);
					mPanel.getlblSendInfo().setText("Wiadomoœæ wys³ana.");
					
					return null;
				}
			};
			sw.execute();
			
		}

	public SwingWorker<Void, Void> getSwingWorker()
	{
		return this.sw;
	}
	
}
