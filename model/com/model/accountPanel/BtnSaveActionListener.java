package com.model.accountPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.model.PropertiesManager;
import com.model.Strings;
import com.model.mailEngine.MailEngine;
import com.view.accountPanel.AccountsPanel;

public class BtnSaveActionListener implements ActionListener{

	AccountsPanel accPanel;
	
	public BtnSaveActionListener(AccountsPanel accPanel)
	{
		this.accPanel = accPanel;
	}
	
	
		public void actionPerformed(ActionEvent e)
		{
			String account = accPanel.getTxtFieldAccountName().getText();
			String user = accPanel.getTxtFieldUser().getText();
			String mail = accPanel.getTxtFieldMail().getText();
			String pass = accPanel.getTxtFieldPass().getText();
			String smtp = accPanel.getTxtFieldSMTP().getText();
			String imap = accPanel.getTxtFieldIMAP().getText();
			
			if(account.equalsIgnoreCase("") || user.equalsIgnoreCase("") ||
					mail.equalsIgnoreCase("") || pass.equalsIgnoreCase("") ||
					smtp.equals("") || imap.equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(accPanel.getWindow(), Strings.AP_saveOptErr[Strings.i]);
			}
			else
			{	// zapisuje nowe konto
				if(accPanel.getBtnSave().getText().equalsIgnoreCase("Dodaj konto"))
				{
					PropertiesManager properties = new PropertiesManager(account);
					
					boolean exists = properties.create(account, "config.properties"); 
					
					if(!exists)
					{
						properties.setProperty("account", account);
						properties.setProperty("user", user);
						properties.setProperty("mail", mail);
						properties.setProperty("pass", pass);
						properties.setProperty("smtp", smtp);
						properties.setProperty("imap", imap);
						System.out.println("zaladowano nowe");
						accPanel.addProfileToCombo(account);
						int currSize = MailEngine.getGeneralProperties().getSize();
						
						if((currSize) == 0)
						{
							System.out.println("ilosc kont == 1");
							MailEngine.getGeneralProperties().setProperty("account_" + (currSize+1), account);
							MailEngine.getGeneralProperties().setProperty("current_acc", account);
							MailEngine.getCurrentProperties().copy(properties);
							accPanel.getLblCurrAcc().setText(account);
						}
						else
						{
							MailEngine.getGeneralProperties().setProperty("account_" + (currSize), account);
						}
						
						MailEngine.getCurrentProperties().show();	
					}
					else
					{
						System.out.println("istnieje juz takie konto");
					}
					
					// reseting all fields.
					accPanel.getTxtFieldAccountName().setText("");
					accPanel.getTxtFieldIMAP().setText("");
					accPanel.getTxtFieldMail().setText("");
					accPanel.getTxtFieldPass().setText("");
					accPanel.getTxtFieldSMTP().setText("");
					accPanel.getTxtFieldUser().setText("");
					
					JOptionPane.showMessageDialog(accPanel.getWindow(), Strings.AP_saveOptInfo[Strings.i] + account, "Info", JOptionPane.INFORMATION_MESSAGE);
					AccountsPanel.isComboBoxListenerActive = true;
				}
				else
				{
					// zapisuje zmiany w istniejacym koncie...
					System.out.println("\n\n BEFORE EDIT:");
					accPanel.getCurrSelProperties().show();
					
					accPanel.getCurrSelProperties().setProperty("user", user);
					accPanel.getCurrSelProperties().setProperty("mail", mail);
					accPanel.getCurrSelProperties().setProperty("pass", pass);
					accPanel.getCurrSelProperties().setProperty("smtp", smtp);
					accPanel.getCurrSelProperties().setProperty("imap", imap);
					System.out.println("\n\n AFTER EDIT:");
					accPanel.getCurrSelProperties().show();
					System.out.println("\n\n\n");
				}
			}
		}


}
