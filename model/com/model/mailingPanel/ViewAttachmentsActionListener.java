package com.model.mailingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.view.mailingPanel.AttachmentsView;
import com.view.mailingPanel.MailingPanel;

public class ViewAttachmentsActionListener implements ActionListener
{
	MailingPanel panel;
	AttachmentsView attachmentsView;
	public ViewAttachmentsActionListener(MailingPanel panel)
	{
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e)
	{
		
		attachmentsView = new AttachmentsView(panel);
	}
	
	public AttachmentsView getAttachmentsView()
	{
		return this.attachmentsView;
	}
}
