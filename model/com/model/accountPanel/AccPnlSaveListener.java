package com.model.accountPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.model.PropertiesManager;
import com.model.mailEngine.MailEngine;
import com.view.accountPanel.AccountsPanel;

public class AccPnlSaveListener implements ActionListener {

	AccountsPanel accPnl;
	
	public AccPnlSaveListener(AccountsPanel accPnl)
	{
		this.accPnl = accPnl;
	}
	
		public void actionPerformed(ActionEvent e)
		{
			String account = accPnl.getTxtFieldAccountName().getText();
			String user = accPnl.getTxtFieldUser().getText();
			String mail = accPnl.getTxtFieldMail().getText();
			String pass = accPnl.getTxtFieldPass().getText();
			String smtp = accPnl.getTxtFieldSMTP().getText();
			String imap = accPnl.getTxtFieldIMAP().getText();
			
			if(account.equalsIgnoreCase("") || user.equalsIgnoreCase("") ||
					mail.equalsIgnoreCase("") || pass.equalsIgnoreCase("") ||
					smtp.equals("") || imap.equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(accPnl.getWindow(), "Some fields are blank!");
			}
			else
			{
				PropertiesManager properties = new PropertiesManager("account_specific");
				
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
					accPnl.addProfileToCombo(account);
					int currSize = MailEngine.getGeneralProperties().getSize();
					
					if((currSize) == 0)
					{
						System.out.println("ilosc kont == 1");
						MailEngine.getGeneralProperties().setProperty("account_" + (currSize+1), account);
						MailEngine.getGeneralProperties().setProperty("current_acc", account);
						MailEngine.getCurrentProperties().copy(properties);
						accPnl.getLblCurrAcc().setText(account);
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
				accPnl.getTxtFieldAccountName().setText("");
				accPnl.getTxtFieldIMAP().setText("");
				accPnl.getTxtFieldMail().setText("");
				accPnl.getTxtFieldPass().setText("");
				accPnl.getTxtFieldSMTP().setText("");
				accPnl.getTxtFieldUser().setText("");
				
				JOptionPane.showMessageDialog(accPnl.getWindow(), "Dodano nowe konto: " + account, "Info", JOptionPane.INFORMATION_MESSAGE);
//				AccountsPanel.setActionListenerActive(true);
			}
		}
	}
