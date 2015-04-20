package com.model.mailingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.model.MessageOut;
import com.model.Strings;
import com.model.mailEngine.MailEngine;
import com.model.mailEngine.MyMessageHolder;
import com.view.mailingPanel.MailingPanel;


/**
 * This actionListener is conected to MailingPanel and namely to
 * 'btnMenu' of this class. Its main purpose is to ask the user
 * about the choise when quitting writing a message.
 * @author Damian
 * @see MailingPanel
 *
 */
public class MailinPnlBackMenuActionListener implements ActionListener{
	
	MailingPanel panel;
	
	public MailinPnlBackMenuActionListener(MailingPanel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) 
	{
		int quit = 1;


		/*
		 * tu mo¿na by dodaæ coœ o anulowaniu dzia³aj¹cego swingWorkera.
		 */
		
		
		
		if(isEveryFieldBlank(this.panel))
		{
			
			//ukrywam panel maili i odkrywam panel glowny
			this.panel.setVisible(false);
			this.panel.getWindows().getHelloPanel().setVisible(true);
			this.panel.getWindows().setDefaultSize();
			this.panel.getWindows().setLocationRelativeTo(null);
		}
		else
		{
			quit =	JOptionPane.showConfirmDialog(this.panel, "<html><p align=\'center\'>Wychodzê.<br><br> Dodaæ do kopii roboczych?</p></html>",
					"Oops!", JOptionPane.YES_NO_OPTION);

			if(quit == JOptionPane.YES_OPTION) 
			{	
				System.out.println("quit: " + quit);
				
				MessageOut mOut = new MessageOut(this.panel.getTxtFieldEmailAdr().getText(),
												this.panel.getTxtFieldSubject().getText(),
												this.panel.getTxtMessageArea().getText());
				MyMessageHolder.addDraft(mOut);
				MyMessageHolder.saveDrafts();
				panel.getWindows().getDraftsPanel().getDraftsList().updateDlm();
				panel.getWindows().getDraftsPanel().getPnlNorthDrafts().getNDMCP().updateMessCountLbl();
				
				// czyszcze okno
				this.panel.getTxtFieldEmailAdr().setText("");
				this.panel.getTxtFieldSubject().setText("");
				this.panel.getTxtMessageArea().setText("");
				this.panel.getlblSendInfo().setText("");
				// zmieniam okno
				this.panel.setVisible(false);
				this.panel.getWindows().getHelloPanel().setVisible(true);
				this.panel.getWindows().setSize(340, 400);
				
				// ustalam imie wlasciciela na panelu glownym
				if(MailEngine.getCurrentProperties() != null)
				{
					this.panel.getWindows().setTitle(Strings.MPBMAL_hi1[Strings.i] + 
					MailEngine.getCurrentProperties().getProperty("user") + "!");
				} 
				else 
				{
					this.panel.getWindows().setTitle(Strings.MPBMAL_hi2[Strings.i]);
				}
			}
			else
			{
				this.panel.getTxtFieldEmailAdr().setText("");
				this.panel.getTxtFieldSubject().setText("");
				this.panel.getTxtMessageArea().setText("");
				// zmieniam okno
				this.panel.setVisible(false);
				this.panel.getWindows().getHelloPanel().setVisible(true);
				this.panel.getWindows().setDefaultSize();
				this.panel.getWindows().setLocationRelativeTo(null);
			}
		}
	}
	
	/**
	 * Patrze, czy wszystkie pola sa puste
	 * @param panel
	 * @return
	 */
	private boolean isEveryFieldBlank(MailingPanel panel) 
	{
		boolean isEmpty = false;
		if ((this.panel.getTxtFieldEmailAdr().getText().equals(""))
		&& (this.panel.getTxtFieldSubject().getText().equals(""))
		&& (this.panel.getTxtMessageArea().getText().equals("")))
		{
			isEmpty = true;
			System.out.println("wszystko jest puste!");
		}
		
		return isEmpty;
	}
}