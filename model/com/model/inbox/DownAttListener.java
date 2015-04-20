package com.model.inbox;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.model.mailEngine.MyMessage;
import com.view.inbox.DownAttFrame;

public class DownAttListener implements MouseListener
{

	DownAttFrame frame;
	
	public DownAttListener(DownAttFrame frame)
	{
		this.frame = frame;
	}
	
	
	
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount() == 2)
		{
			System.out.println("double click");
			MyMessage m = frame.getInboxPnl().getCurrentMessage();
			System.out.println(m.toSimpleString());
			int ind = frame.getInboxPnl().getSouthPanel().
				getDownAttFrame().getJlist().locationToIndex(e.getPoint());
			
			String attFileName = 
					frame.getInboxPnl().getSouthPanel().getDownAttFrame().
					getDlm().getElementAt(ind).getFile().getName();
			
			frame.getInboxPnl().getWindow().getMailEngine().readToDownloadAtt(m, attFileName);
			System.out.println("end of mouse clicked method.");
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	
}
