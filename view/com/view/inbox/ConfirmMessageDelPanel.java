package com.view.inbox;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Strings;
import com.model.inbox.ConfirmMessDelListener;


/**
 * A small panel class which appears in the SouthInboxPanel
 * after clicking a "Delete" button.
 * @author Damian
 * @see SouthInboxPanel
 * @see InboxPanel
 */
public class ConfirmMessageDelPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InboxPanel inboxPanel;
	JLabel lblMessage;
	JButton btnYes;
	JButton btnNo;
	public ConfirmMessageDelPanel(InboxPanel inboxPanel)
	{
		this.inboxPanel = inboxPanel;
		setBackground(Color.RED);

		this.setLayout(new FlowLayout());
		
		lblMessage = new JLabel(Strings.CMD_lblMes[Strings.i]);
		this.add(lblMessage);
		
		btnYes = new JButton(Strings.CMD_btnYes[Strings.i]);
		btnYes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnYes.addActionListener(new ConfirmMessDelListener(inboxPanel));
		this.add(btnYes);
		
		btnNo = new JButton(Strings.CMD_btnNo[Strings.i]);
		btnNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				inboxPanel.getSouthPanel().getBtnReply().setVisible(true);
				inboxPanel.getSouthPanel().getBtnDel().setVisible(true);
				setVisible(false);
			}
		});
		this.add(btnNo);
		
		this.setVisible(false);
	}
}
