package com.model.drafts;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.model.MessageOut;
import com.model.Strings;
import com.view.drafts.DraftsContentPanel;
import com.view.drafts.DraftsPanel;

public class DraftDoubleClickedListener implements MouseListener{

	
	DraftsPanel pnlDrafts;
	
	public DraftDoubleClickedListener(DraftsPanel pnlDrafts)
	{
		this.pnlDrafts =pnlDrafts;
	}
	
	
	public void mouseClicked(MouseEvent e) 
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if(e.getClickCount() == 1)
			{
				this.pnlDrafts.getSplitPane().setDividerLocation(160);
				int ind = this.pnlDrafts.getDraftsList().locationToIndex(e.getPoint());
				
				MessageOut m = (MessageOut) this.pnlDrafts.getDraftsList().getDLM().getElementAt(ind);
				this.pnlDrafts.setCurrentDraft(m);
				this.pnlDrafts.getPnlNorthDrafts().getNorthWestDraftsPnl().setInfoLabelInSouthPnl(m.getReceiver(), m.getDate());
				
				try 
				{
					pnlDrafts.setDraftsContentPnl(new DraftsContentPanel(m, pnlDrafts));
					
					JScrollPane scroll = new JScrollPane(pnlDrafts.getDraftsContentPnl());
					scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					this.pnlDrafts.getSplitPane().setRightComponent(pnlDrafts.getDraftsContentPnl());
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
	}

	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	
	
}
