package com.model.accountPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.model.PropertiesManager;
import com.model.Strings;
import com.model.mailEngine.MailEngine;
import com.view.accountPanel.AccountsPanel;

public class ComboBoxActListener implements ActionListener{

	AccountsPanel accPanel;
	
	public ComboBoxActListener(AccountsPanel accPanel)
	{
		this.accPanel = accPanel;
	}
	
		public void actionPerformed(ActionEvent e)
		{
			if(AccountsPanel.isComboBoxListenerActive)
			{
				JComboBox<String> box = (JComboBox <String>) e.getSource();
				String item = (String) box.getSelectedItem();

				accPanel.setCurrentlySelectedProperties(new PropertiesManager(item));
				accPanel.getCurrSelProperties().load(item, "config.properties");
				
				String [] options = {Strings.AP_optionCurr[Strings.i],
									Strings.AP_optionEdit[Strings.i]};
				
				int answ = JOptionPane.showOptionDialog(accPanel.getWindow(),
										Strings.AP_optionMes[Strings.i],
										Strings.AP_optionHed[Strings.i],
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										null,
										options,
										options[1]);
				
				//yes - set as default
				if(answ == JOptionPane.YES_OPTION)
				{
					System.out.println("yes");
					accPanel.getLblCurrAcc().setText(item);
					MailEngine.getCurrentProperties().copy(accPanel.getCurrSelProperties());
					MailEngine.getGeneralProperties().setProperty("current_acc", item);
					MailEngine.init();
				}
				// no - edit
				else if(answ == JOptionPane.NO_OPTION)
				{
					accPanel.getEditAccountPanel().setVisible(true);
					accPanel.getBtnSave().setText(Strings.AP_btnSave[Strings.i]);
					accPanel.getBtnDel().setEnabled(true);
					accPanel.getTxtFieldAccountName().setText(accPanel.getCurrSelProperties().getProperty("account"));
					accPanel.getTxtFieldIMAP().setText(accPanel.getCurrSelProperties().getProperty("imap"));
					accPanel.getTxtFieldSMTP().setText(accPanel.getCurrSelProperties().getProperty("smtp"));
					accPanel.getTxtFieldMail().setText(accPanel.getCurrSelProperties().getProperty("mail"));
					accPanel.getTxtFieldPass().setText(accPanel.getCurrSelProperties().getProperty("pass"));
					accPanel.getTxtFieldUser().setText(accPanel.getCurrSelProperties().getProperty("user"));
					System.err.println("no");
					
					accPanel.getTxtFieldAccountName().setEditable(false);
					accPanel.getTxtFieldAccountName().setToolTipText("Niestety nie mo¿esz edytowaæ tego pola");
				}
			}	
		}

	
	
	
}
