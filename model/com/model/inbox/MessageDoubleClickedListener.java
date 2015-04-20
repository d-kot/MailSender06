package com.model.inbox;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.model.Strings;
import com.model.mailEngine.MyMessage;
import com.model.mailEngine.MyMessageHolder;
import com.view.inbox.InboxPanel;
import com.view.inbox.MessageContentPanel;
import com.view.inbox.InboxMessageList;



/**
 * This class implements double clicking on a message in the inbox panel.
 * 'public void mouseClicked(MouseEvent)' method resizes the
 * divider location of the JSplitPane from the SingleMessageInbox class.
 * @see InboxPanel
 * @see InboxMessageList
 * @author Damian
 *
 */
public class MessageDoubleClickedListener implements MouseListener{

	
	InboxPanel sInbox;
	
	public MessageDoubleClickedListener(InboxPanel sInbox) 
	{
		this.sInbox = sInbox;
	}
	
	
	public void mouseClicked(MouseEvent e) 
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if(e.getClickCount() == 1)
			{
				this.sInbox.getSplitPane().setDividerLocation(160);
				int ind = this.sInbox.getMessageList().locationToIndex(e.getPoint());
				
				MyMessage m = (MyMessage) this.sInbox.getMessageList().getDLM().getElementAt(ind);
				this.sInbox.setCurrentMessage(m);
				this.sInbox.getSouthPanel().setInfoLabelInSouthPnl(m.getSender(), m.getSendDate());
				if(m.getAttachmentsList() != null)
				{
					if(m.getAttachmentsList().size() != 0)
					{
						this.sInbox.getSouthPanel().getBtnAttachment().setText(Strings.MDCL_lblAtt[Strings.i] + m.getAttachmentsList().size() + "");
					}
				}
				this.sInbox.getSouthPanel().getConfirmDelPnl().setVisible(false);
				this.sInbox.getSouthPanel().getBtnDel().setVisible(true);
				if(! (m.isRead()))
				{
					m.setRead(true);
					this.sInbox.getNorthPanel().getNIMCP().updateMessCountLbl();
					MyMessageHolder.save();
				}
				
				try 
				{
					sInbox.setMessageContentPanel(new MessageContentPanel(m, this.sInbox));
					
					JScrollPane scroll = new JScrollPane(sInbox.getMessageContentPanel());
					scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					this.sInbox.getSplitPane().setRightComponent(sInbox.getMessageContentPanel());
					this.sInbox.getSouthPanel().setVisible(true);
					
					if(m.hasAtt())
					{
						this.sInbox.getSouthPanel().getBtnAttachment().setVisible(true);
					}
					else
					{
						this.sInbox.getSouthPanel().getBtnAttachment().setVisible(false);
					}
					
					System.out.println("visible index: " + ind);
					System.out.println(MyMessageHolder.getUnread());
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
			}
		}
		if(SwingUtilities.isRightMouseButton(e))
		{
			System.out.println("right clicked");
			int ind = this.sInbox.getMessageList().locationToIndex(e.getPoint());
			MyMessage m = (MyMessage) this.sInbox.getMessageList().getDLM().getElementAt(ind);
		}
	}

	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
