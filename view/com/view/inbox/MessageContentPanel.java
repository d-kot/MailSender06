package com.view.inbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.model.inbox.MessageDoubleClickedListener;
import com.model.mailEngine.MyMessage;

/**
 * This class shows textual content of messages and
 * is a bottm part of JSplitPane of the class InboxPanel.
 * However, its called not in the "InboxPanel" class but
 * in the "MessageDoubleClickedListener" because it appears
 * after double clicking on a message.
 * @see MessageDoubleClickedListener
 * @see InboxPanel
 * @author Damian
 *
 */
public class MessageContentPanel extends JPanel 
{
	MyMessage message;
	InboxPanel inbox;
	JTextArea textArea;

	public MessageContentPanel(MyMessage m, InboxPanel panel) throws IOException
	{
		textArea = new JTextArea(m.getText());
		textArea.setOpaque(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setMargin(new Insets(2,5,5,5));
		
		this.setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		this. message = m;
		this.inbox = panel;
	
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
