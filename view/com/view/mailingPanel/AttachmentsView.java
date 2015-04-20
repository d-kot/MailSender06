package com.view.mailingPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.model.Strings;
import com.model.mailingPanel.Attachment;
import com.model.mailingPanel.DeleteAttListener;

/**
 * After adding an attachment to a new message in
 * "MailingPanel", a new button "btnSeeAttach"
 * becomes visible. After clicking that button
 * this small JFrame pops up. Its main puprpose is
 * to allow a user to delete attachments easily using
 * "DeleteAttListner".
 * @see DeleteAttListener
 * @author Damian
 *
 */
public class AttachmentsView extends JFrame{
	private static final long serialVersionUID = 1L;
	MailingPanel panel;
	JList<Attachment> list;
	DefaultListModel<Attachment> dlm;
	JPanel mainPanel;
	
	
	public AttachmentsView(MailingPanel panel)
	{
		this.panel= panel;
		
		this.setTitle(Strings.AV_title[Strings.i]);
		this.setSize(370,400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 370, 420);
		this.add(mainPanel);
		mainPanel.setLayout(null);
		
		dlm = new DefaultListModel<>();
		
		System.out.println("zaraz cos dodam");
		for(Attachment a : panel.getAttachments())
		{
			System.out.println("adding: " + a.getFile().getName());
			dlm.addElement(a);
			
		}
		
		list = new JList<>(dlm);
		list.addMouseListener(new DeleteAttListener(this));
		list.setLayout(new FlowLayout());
		list.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mainPanel.add(list);

		JScrollPane scrollContactsList = new JScrollPane(list);
		scrollContactsList.setBounds(5, 5, 345, 320);
		mainPanel.add(scrollContactsList);
		
		JLabel info = new JLabel(Strings.AV_lblInfo[Strings.i]);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setVerticalAlignment(JLabel.CENTER);
		info.setBounds(5, 330, 345, 25);
		info.setFont(new Font("Tahoma", Font.PLAIN, 12));
		info.setBackground(Color.BLUE);
		info.setForeground(Color.white);
		info.setOpaque(true);
		mainPanel.add(info);
		
		this.add(mainPanel);
		this.setLocationRelativeTo((JPanel) panel);
		this.setVisible(true);
	}

	public DefaultListModel<Attachment> getDlm()
	{
		return this.dlm;
	}
	
	public JList<Attachment> getJlist()
	{
		return this.list;
	}
	
	public MailingPanel getMailingPanel()
	{
		return this.panel;
	}
	
	
}
