package com.model.mailEngine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MailViewer extends JFrame {

	
	
	private static final long serialVersionUID = 1L;

	public MailViewer(String text, Message m)
	{
		this.setSize(950,650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String info = "";
		try 
		{
			info =	"Od:     " + m.getFrom()[0].toString() + "\n" +
					"Data:   " + m.getSentDate() +  "\n" +
					"Temat:  " + m.getSubject() + "\n" + 

					"______________________________________\n\n";
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		
//		MessageContentPanel panel = new MessageContentPanel();
//		this.add(panel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
}



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
class MessageContentPanel extends JPanel 
{
	MyMessage message;
//	InboxPanel inbox;
	JTextArea textArea;

	public MessageContentPanel(MyMessage m) throws IOException
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
	//	this.inbox = panel;
	
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
	//	this.add(new SouthInboxPanel(inbox), BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	public JTextArea getMessageContentArea()
	{
		return textArea;
	}
	
	
}
