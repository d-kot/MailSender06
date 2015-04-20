package com.view.inbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Strings;
import com.model.mailEngine.MyMessage;

/**
 * This class presents yellow bottom panel in the "InboxPanel"
 *
 * @see InboxPanel
 * @see NorthInboxPanel
 * @See ConfirmMessageDelPanel
 * @author Damian
 *
 */
//public class SouthInboxPanel extends JPanel
//{
//	private static final long serialVersionUID = 1L;
//	InboxPanel inbox;
//	ConfirmMessageDelPanel confirmPanel;
//	JButton btnReply;
//	JButton btnDownloadAtt;
//	JButton btnDelete;
//	JPanel pnlFirstRow,
//			pnlSecRow,
//			pnlThirdRow;
//	
//	public SouthInboxPanel(InboxPanel inboxPanel)
//	{
//		this.inbox = inboxPanel;
//		setBackground(Color.yellow);
//		
//		confirmPanel = new ConfirmMessageDelPanel(inbox);
//		
//		btnReply =  new JButton("Reply");
//		btnReply.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		
//		btnReply.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				MyMessage m = inbox.getCurrentMessage();
//				inbox.setVisible(false);
//				inbox.getWindow().getMailingPanel().getTxtFieldEmailAdr().setText(m.getSender());
//				
//				inbox.getWindow().getMailingPanel().getTxtFieldSubject().setText("Re: " + m.getSubject());
//				inbox.getWindow().getMailingPanel().getTxtMessageArea().setText(m.getText()+ "\n____________________________\n\n\n\n");
//				inbox.getWindow().getMailingPanel().setVisible(true);
//				inbox.getWindow().getMailingPanel().getTxtMessageArea().requestFocus();;
//			}
//		});
//		add(btnReply);
//		
//		btnDownloadAtt =  new JButton("    Za³¹czniki    ");
//		btnDownloadAtt.setVisible(false);
//		btnDownloadAtt.setBackground(Color.YELLOW);
//		btnDownloadAtt.setOpaque(false);
//		btnDownloadAtt.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		add(btnDownloadAtt);
//		
//		
//		btnDelete =  new JButton("Delete");
//		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		
//		btnDelete.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				btnDelete.setVisible(false);
//				confirmPanel.setVisible(true);
//			}
//		});
//		add(btnDelete);
//		
//		add(new JLabel("<html> .<br> <br> </html>"));
//		add(confirmPanel);
//		setVisible(false);
//	}
//	
//	
//	public JButton getBtnDel()
//	{
//		return this.btnDelete;
//	}
//	
//	
//	public JButton getBtnAttachment()
//	{
//		return this.btnDownloadAtt;
//	}
//	
//	public ConfirmMessageDelPanel getConfirmDelPnl()
//	{
//		return this.confirmPanel;
//	}
//}
//
//



public class SouthInboxPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	InboxPanel inbox;
	ConfirmMessageDelPanel confirmPanel;
	JButton btnReply;
	JButton btnDownloadAtt;
	JButton btnDelete;
	JPanel pnlFirstRow,
			pnlSecRow,
			pnlThirdRow;
	
	JLabel infoLabel;
	String from;
	String date;
	DownAttFrame downAttFrame;
	
	public SouthInboxPanel(InboxPanel inboxPanel)
	{
		this.inbox = inboxPanel;
		this.setBackground(Color.yellow);
		this.setLayout(new GridLayout(1,3));
		confirmPanel = new ConfirmMessageDelPanel(inbox);

		//first left south panel
		pnlFirstRow = new JPanel();
		pnlFirstRow.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlFirstRow.setOpaque(true);
		pnlFirstRow.setBackground(Color.DARK_GRAY);
		pnlFirstRow.setPreferredSize(new Dimension(400, 40));
		
		infoLabel = new JLabel("<html><br><br></html>", JLabel.LEFT);

		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		infoLabel.setForeground(Color.white);
		pnlFirstRow.add(infoLabel);
		this.add(pnlFirstRow);
		
		
		
		
		//second center panel
		pnlSecRow = new JPanel();
		pnlSecRow.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlSecRow.setOpaque(true);
		pnlSecRow.setBackground(Color.DARK_GRAY);
		pnlSecRow.setPreferredSize(new Dimension(250, 40));
		btnReply =  new JButton(Strings.SIP_btnReply[Strings.i]);
		btnReply.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				MyMessage m = inbox.getCurrentMessage();
				inbox.setVisible(false);
				inbox.getWindow().getMailingPanel().getTxtFieldEmailAdr().setText(m.getSender());
				
				inbox.getWindow().getMailingPanel().getTxtFieldSubject().setText("Re: " + m.getSubject());
				inbox.getWindow().getMailingPanel().getTxtMessageArea().setText(m.getText()+ "\n____________________________\n\n\n\n");
				inbox.getWindow().getMailingPanel().setVisible(true);
				inbox.getWindow().getMailingPanel().getTxtMessageArea().requestFocus();;
			}
		});
		pnlSecRow.add(btnReply);
		
		btnDelete =  new JButton(Strings.SIP_btnDel[Strings.i]);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				btnReply.setVisible(false);
				btnDelete.setVisible(false);
				confirmPanel.setVisible(true);
			}
		});
		pnlSecRow.add(btnDelete);
		pnlSecRow.add(confirmPanel);
		
		this.add(pnlSecRow);
		
		
		// third panel
		pnlThirdRow = new JPanel();
		pnlThirdRow.setOpaque(true);
		pnlThirdRow.setBackground(Color.DARK_GRAY);
		btnDownloadAtt =  new JButton("");
		btnDownloadAtt.setFocusPainted(false);
		btnDownloadAtt.setVisible(false);
		btnDownloadAtt.setPreferredSize(new Dimension(250,30));
		btnDownloadAtt.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDownloadAtt.setBackground(Color.DARK_GRAY);
		btnDownloadAtt.setForeground(Color.white);
		btnDownloadAtt.setOpaque(false);
		
		btnDownloadAtt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				inbox.getCurrentMessage().getAttachmentsList();
				downAttFrame = new DownAttFrame(inbox);
			}
		});
		pnlThirdRow.add(btnDownloadAtt);
		
		this.add(pnlThirdRow);
		

		setVisible(false);
	}
	
	
	public JButton getBtnDel()
	{
		return this.btnDelete;
	}
	
	public JButton getBtnReply()
	{
		return this.btnReply;
	}
	
	public JButton getBtnAttachment()
	{
		return this.btnDownloadAtt;
	}
	
	public ConfirmMessageDelPanel getConfirmDelPnl()
	{
		return this.confirmPanel;
	}
	
	public void setInfoLabelInSouthPnl(String s, String d)
	{
		this.from = s;
		this.date = d;
		infoLabel.setText("<html><b>" + Strings.SIP_lblFrom[Strings.i] + "</b> &nbsp&nbsp " + s + "<br>" +
								"<b>" + Strings.SIP_lblDate[Strings.i]+"</b> " + d + "</html>");
	}
	
	public DownAttFrame getDownAttFrame()
	{
		return this.downAttFrame;
	}
}


