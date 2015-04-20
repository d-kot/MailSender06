package com.view.drafts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.model.MessageOut;
import com.view.inbox.InboxPanel;

public class DraftsContentPanel extends JPanel{

	MessageOut message;
	DraftsPanel pnlDrafts;
	JTextArea textArea;

	public DraftsContentPanel(MessageOut m, DraftsPanel panel) throws IOException
	{
		textArea = new JTextArea(m.getMesContent());
		textArea.setOpaque(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setMargin(new Insets(2,5,5,5));
		
		this.setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		this. message = m;
		this.pnlDrafts = panel;
	
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public JTextArea getMessageContentArea()
	{
		return textArea;
	}
	
	
}
