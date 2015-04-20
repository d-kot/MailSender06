package com.model.mailingPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.model.Strings;
import com.view.mailingPanel.AttachmentsView;
import com.view.mailingPanel.MailingPanel;

/**
 * This class implements "MouseListener" interface and
 * its main purpose is to delete attachments from "AttachmentsView"
 * frame which in turns pops up to show added attachemtns.
 * It deletes attachments after a double click on it.
 * @see AttachmentsView
 * @see MailingPanel
 * @author Damian
 *
 */
public class DeleteAttListener implements MouseListener {
	
	AttachmentsView view;
	public DeleteAttListener(AttachmentsView view)
	{
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2)
		{
			int ans = JOptionPane.showConfirmDialog(view, Strings.DAL_opt1[Strings.i]);
			if(ans == JOptionPane.YES_OPTION)
			{
				System.out.println("deleting...");
				int index = view.getJlist().locationToIndex(e.getPoint());
				Attachment toBeRemoved = view.getDlm().elementAt(index);
				view.getMailingPanel().removeAttachment(toBeRemoved);
				view.getDlm().remove(index);
				int currAttNr = view.getMailingPanel().getAttachments().size();
				view.getMailingPanel().getBtnSeeAtt().setText(Strings.DAL_btnAttNr[Strings.i] + currAttNr + ".");
				if(view.getDlm().size() == 0)
				{
					view.getMailingPanel().getBtnSeeAtt().setVisible(false);
					view.setVisible(false);
				}
			}
			else
			{
				System.out.println("deleting canceled.");
			}
		}
	}

	public void mouseEntered(MouseEvent arg0)
	{
		
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
	
	
	
}
