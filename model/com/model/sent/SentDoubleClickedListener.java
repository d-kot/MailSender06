package com.model.sent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.model.MessageOut;
import com.view.sent.SentContentPanel;
import com.view.sent.SentPanel;

public class SentDoubleClickedListener implements MouseListener{

	
	
	SentPanel pnlSent;
	SentContentPanel sentContentPanel = null;
	public SentDoubleClickedListener(SentPanel pnlSent)
	{
		this.pnlSent =pnlSent;
	}
	
	
	public void mouseClicked(MouseEvent e) 
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if(e.getClickCount() == 1)
			{
				this.pnlSent.getSplitPane().setDividerLocation(160);
				int ind = this.pnlSent.getSentList().locationToIndex(e.getPoint());
				
				MessageOut m = (MessageOut) this.pnlSent.getSentList().getDLM().getElementAt(ind);
				this.pnlSent.setCurrentSent(m);
				this.pnlSent.getPnlNorthSent().getNorthWestSentPnl().setInfoLabelInSouthPnl(m.getReceiver(), m.getDate());
//				if(m.getAttachmentsList() != null)
//				{
//					if(m.getAttachmentsList().size() != 0)
//					{
//						this.sInbox.getSouthPanel().getBtnAttachment().setText(Strings.MDCL_lblAtt[Strings.i] + m.getAttachmentsList().size() + "");
//					}
//				}

//				this.sInbox.getSouthPanel().getConfirmDelPnl().setVisible(false);
//				this.sInbox.getSouthPanel().getBtnDel().setVisible(true);

//				if(! (m.isRead()))
//				{
//					m.setRead(true);
//					this.sInbox.getNorthPanel().getNIMCP().updateMessCountLbl();
//					MyMessageHolder.save();
//				}
				
				
				
				try 
				{
					sentContentPanel = new SentContentPanel(m, pnlSent);
					pnlSent.setSentContentPnl(sentContentPanel);
					
					JScrollPane scroll = new JScrollPane(pnlSent.getSentContentPnl());
					scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					this.pnlSent.getSplitPane().setRightComponent(pnlSent.getSentContentPnl());
//					this.pnlDrafts.getSouthPanel().setVisible(true);
					
//					if(m.hasAtt())
//					{
//						this.sInbox.getSouthPanel().getBtnAttachment().setVisible(true);
//					}
//					else
//					{
//						this.sInbox.getSouthPanel().getBtnAttachment().setVisible(false);
//					}
					
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
			int ind = this.pnlSent.getSentList().locationToIndex(e.getPoint());
			MessageOut mOut = (MessageOut) this.pnlSent.getSentList().getDLM().getElementAt(ind);
		}
	}

	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	

}
