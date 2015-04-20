package com.model.accountPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.model.Strings;
import com.model.mailEngine.MyMessageHolder;
import com.view.accountPanel.AccountsPanel;

public class AccPnlMenuListener implements ActionListener{

	
	AccountsPanel accPnl;
	
	public AccPnlMenuListener(AccountsPanel accPnl)
	{
		this.accPnl = accPnl;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		AccountsPanel.isComboBoxListenerActive = true;	
			if
			(
				(!accPnl.getTxtFieldAccountName().getText().equalsIgnoreCase("")) || 
				(!accPnl.getTxtFieldMail().getText().equalsIgnoreCase("")) ||
				(!accPnl.getTxtFieldPass().getText().equalsIgnoreCase("")) ||
				(!accPnl.getTxtFieldSMTP().getText().equalsIgnoreCase("")) ||
				(!accPnl.getTxtFieldIMAP().getText().equalsIgnoreCase("")) ||
				(!accPnl.getTxtFieldUser().getText().equalsIgnoreCase(""))
			)
				{
					int answ = JOptionPane.showConfirmDialog(
							accPnl.getWindow(), Strings.AP_menu[Strings.i], Strings.AP_menuHed[Strings.i], JOptionPane.YES_NO_OPTION);
					if(answ == JOptionPane.YES_OPTION)
					{
						accPnl.getTxtFieldAccountName().setText("");
						accPnl.getTxtFieldIMAP().setText("");
						accPnl.getTxtFieldMail().setText("");
						accPnl.getTxtFieldPass().setText("");
						accPnl.getTxtFieldSMTP().setText("");
						accPnl.getTxtFieldUser().setText("");
						
						accPnl.getEditAccountPanel().setVisible(false);
						accPnl.setVisible(false);
						accPnl.getWindow().getHelloPanel().setVisible(true);
						accPnl.getWindow().setDefaultSize();
					}
				}
				else
				{
					accPnl.getWindow().getInbox().getMessageList().getDLM().removeAllElements();
					MyMessageHolder.switchInbox();
					accPnl.getWindow().getInbox().getMessageList().updateDlm();
					
					accPnl.getWindow().getDraftsPanel().getDraftsList().getDLM().removeAllElements();
					MyMessageHolder.switchDrafts();
					accPnl.getWindow().getDraftsPanel().getDraftsList().updateDlm();
					accPnl.getWindow().getDraftsPanel().getPnlNorthDrafts().getNDMCP().updateMessCountLbl();

					accPnl.getWindow().getSentPanel().getSentList().getDLM().removeAllElements();
					MyMessageHolder.switchSent();
					accPnl.getWindow().getSentPanel().getSentList().updateDlm();
					accPnl.getWindow().getSentPanel().getPnlNorthSent().getNSMCP().updateMessCountLbl();
					
					
					
					accPnl.getEditAccountPanel().setVisible(false);
					accPnl.setVisible(false);
					accPnl.getWindow().getHelloPanel().setVisible(true);
					accPnl.getWindow().setDefaultSize();
					accPnl.getWindow().setLocationRelativeTo(null);

				}
	}
	
}
