package com.model.mailingPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.model.Strings;
import com.view.mailingPanel.MailingPanel;

public class AttachFileActionListener implements ActionListener 
{

	MailingPanel panel;
	
	public AttachFileActionListener(MailingPanel panel)
	{
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(panel);
		File f = chooser.getSelectedFile();
		if(f != null)
		{
			System.out.println("selected: " + f.getName());
			panel.addAttachment(new Attachment(f));
			panel.getBtnSeeAtt().setVisible(true);
			panel.getBtnSeeAtt().setBackground(Color.blue);
			panel.getBtnSeeAtt().setText(Strings.AFAL_btnFile[Strings.i] + panel.getAttachments().size() + ".");
			panel.getBtnSeeAtt().setToolTipText(Strings.AFAL_toolTip[Strings.i]);
		}
	}
	
}
