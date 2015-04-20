package com.view.accountPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.model.PropertiesManager;
import com.model.Strings;
import com.model.accountPanel.AccPnlMenuListener;
import com.model.accountPanel.BtnSaveActionListener;
import com.model.accountPanel.ComboBoxActListener;
import com.model.mailEngine.MailEngine;
import com.model.mailEngine.MyMessageHolder;
import com.view.helloPanel.MainFrame;

public class AccountsPanel extends JPanel{

	MainFrame window;
	JPanel editAccountPanel;
	JButton btnNewAccountButton;
	JLabel lblCurrAccountName;
	
	JTextField txtFieldAccountName;
	JTextField txtFieldUser;
	JTextField txtFieldMail;
	JPasswordField txtFieldPass;
	JTextField txtFieldSMTP;
	JTextField txtFieldIMAP;
	
	JComboBox<String> comboBoxAccounts;
	
	JButton btnSave;
	JButton btnDel;
	JButton btnMenu;
	
	/* static field below prevents comboBoxActionListener
	 * from performing its job while adding the first account.
	 * without this flag the program showed messages of
	 * 'comboBoxActionListener' after clicking 'save' button
	 * which was undesired.
	 * */
	public static boolean isComboBoxListenerActive = true;
	
	PropertiesManager currentlySelectedProperties;
	
	public AccountsPanel(MainFrame window)
	{
		this.window = window;
		setLayout(null);
		
		btnNewAccountButton = new JButton(Strings.AP_btnNewAcc[Strings.i]);
		btnNewAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewAccountButton.setBounds(10, 58, 110, 23);
		btnNewAccountButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				btnSave.setText("Dodaj konto");
				editAccountPanel.setVisible(true);
				isComboBoxListenerActive = false;
			}
		});
		add(btnNewAccountButton);
		
		
		JLabel lblCurrentAccount = new JLabel(Strings.AP_lblCurrAcc[Strings.i]);
		lblCurrentAccount.setBounds(0, 0, 180, 40);
		lblCurrentAccount.setOpaque(true);
		lblCurrentAccount.setBackground(Color.blue);
		lblCurrentAccount.setForeground(Color.WHITE);
		add(lblCurrentAccount);
		
		lblCurrAccountName = new JLabel("...");
		lblCurrAccountName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCurrAccountName.setOpaque(true);
		lblCurrAccountName.setBackground(Color.blue);
		lblCurrAccountName.setForeground(Color.WHITE);
		lblCurrAccountName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrAccountName.setBounds(116, 0, 350, 40);
		add(lblCurrAccountName);
		
		JLabel lblYourAccounts = new JLabel(Strings.AP_yourAcc[Strings.i]);
		lblYourAccounts.setBounds(52, 97, 96, 14);
		add(lblYourAccounts);
		
		comboBoxAccounts = new JComboBox<>();
		comboBoxAccounts.setBounds(51, 111, 279, 20);
		loadProfilesToComboBox();
		comboBoxAccounts.addActionListener(new ComboBoxActListener(this));
		
		
		
//		comboBoxAccounts.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				if(isComboBoxListenerActive)
//				{
//					JComboBox<String> box = (JComboBox <String>) e.getSource();
//					String item = (String) box.getSelectedItem();
//	
//					currentlySelectedProperties = new PropertiesManager(item);
//					currentlySelectedProperties.load(item, "config.properties");
//					
//					String [] options = {Strings.AP_optionCurr[Strings.i],
//										Strings.AP_optionEdit[Strings.i]};
//					
//					int answ = JOptionPane.showOptionDialog(window,
//											Strings.AP_optionMes[Strings.i],
//											Strings.AP_optionHed[Strings.i],
//											JOptionPane.YES_NO_OPTION,
//											JOptionPane.QUESTION_MESSAGE,
//											null,
//											options,
//											options[1]);
//					
//					//yes - set as default
//					if(answ == JOptionPane.YES_OPTION)
//					{
//						System.out.println("yes");
//						lblCurrAccountName.setText(item);
//						MailEngine.getCurrentProperties().copy(currentlySelectedProperties);
//						MailEngine.getGeneralProperties().setProperty("current_acc", item);
//						MailEngine.init();
//					}
//					// no - edit
//					else if(answ == JOptionPane.NO_OPTION)
//					{
//						editAccountPanel.setVisible(true);
//						btnSave.setText(Strings.AP_btnSave[Strings.i]);
//						btnDel.setEnabled(true);
//						getTxtFieldAccountName().setText(currentlySelectedProperties.getProperty("account"));
//						getTxtFieldIMAP().setText(currentlySelectedProperties.getProperty("imap"));
//						getTxtFieldSMTP().setText(currentlySelectedProperties.getProperty("smtp"));
//						getTxtFieldMail().setText(currentlySelectedProperties.getProperty("mail"));
//						getTxtFieldPass().setText(currentlySelectedProperties.getProperty("pass"));
//						getTxtFieldUser().setText(currentlySelectedProperties.getProperty("user"));
//						System.err.println("no");
//						
//						getTxtFieldAccountName().setEditable(false);
//						getTxtFieldAccountName().setToolTipText("Niestety nie mo¿esz edytowaæ tego pola");
//					}
//				}	
//			}
//		});
		add(comboBoxAccounts);

		
		// editAccountPanel
		editAccountPanel = new JPanel();
		editAccountPanel.setBounds(0, 150, 384, 250);
		editAccountPanel.setLayout(null);
		editAccountPanel.setOpaque(true);
		editAccountPanel.setBackground(Color.LIGHT_GRAY);
		add(editAccountPanel);
		
		
//-------------- ACCOUNT
		JLabel lblAccountName = new JLabel(Strings.AP_lblAcc[Strings.i]);
		lblAccountName.setBounds(10, 14, 96, 14);
		lblAccountName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		editAccountPanel.add(lblAccountName);
		
		txtFieldAccountName = new JTextField();
		txtFieldAccountName.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFieldAccountName.setBounds(98, 11, 276, 20);
		editAccountPanel.add(txtFieldAccountName);
		txtFieldAccountName.setColumns(10);

//-------------- USER	
		JLabel lblUsername = new JLabel(Strings.AP_lblUser[Strings.i]);
		lblUsername.setBounds(10, 39, 96, 14);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		editAccountPanel.add(lblUsername);
		
		txtFieldUser = new JTextField();
		txtFieldUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFieldUser.setColumns(10);
		txtFieldUser.setBounds(98, 36, 276, 20);
		editAccountPanel.add(txtFieldUser);
		
//-------------- EMAIL
		JLabel lblEmailAddress_1 = new JLabel(Strings.AP_lblMail[Strings.i]);
		lblEmailAddress_1.setBounds(10, 64, 96, 14);
		lblEmailAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		editAccountPanel.add(lblEmailAddress_1);

		txtFieldMail = new JTextField();
		txtFieldMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFieldMail.setColumns(10);
		txtFieldMail.setBounds(98, 61, 276, 20);
		editAccountPanel.add(txtFieldMail);
		
//-------------	PASS
		JLabel lblPassword_1 = new JLabel(Strings.AP_lblPass[Strings.i]);
		lblPassword_1.setBounds(10, 89, 96, 14);
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		editAccountPanel.add(lblPassword_1);
				
		txtFieldPass = new JPasswordField();
		txtFieldPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFieldPass.setColumns(10);
		txtFieldPass.setBounds(98, 86, 276, 20);
		editAccountPanel.add(txtFieldPass);
		
//-------------	SMTP	
		JLabel lblSmtpServer_1 = new JLabel(Strings.AP_lblSmtp[Strings.i]);
		lblSmtpServer_1.setBounds(10, 114, 96, 14);
		lblSmtpServer_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		editAccountPanel.add(lblSmtpServer_1);
		
		txtFieldSMTP = new JTextField();
		txtFieldSMTP.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFieldSMTP.setColumns(10);
		txtFieldSMTP.setBounds(98, 111, 276, 20);
		editAccountPanel.add(txtFieldSMTP);
		
//------------- IMAP
		JLabel lblImapServer = new JLabel(Strings.AP_lblImap[Strings.i]);
		lblImapServer.setBounds(10, 139, 96, 14);
		lblImapServer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		editAccountPanel.add(lblImapServer);
		
		txtFieldIMAP = new JTextField();
		txtFieldIMAP.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtFieldIMAP.setColumns(10);
		txtFieldIMAP.setBounds(98, 136, 276, 20);
		editAccountPanel.add(txtFieldIMAP);
		
//------------ edit account buttons
		btnDel = new JButton(Strings.AP_btnDel[Strings.i]);
		btnDel.setBounds(10, 165, 70, 23);
		editAccountPanel.add(btnDel);
		btnDel.setEnabled(false);
		btnDel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String accToDel = getCurrSelProperties().getProperty("account");
				System.out.println("acc to del: " + accToDel);
				
			}
		});
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnSave = new JButton();
		btnSave.setBounds(269, 167, 105, 23);
		editAccountPanel.add(btnSave);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.addActionListener(new BtnSaveActionListener(this));
		
		
//		btnSave.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				String account = getTxtFieldAccountName().getText();
//				String user = getTxtFieldUser().getText();
//				String mail = getTxtFieldMail().getText();
//				String pass = getTxtFieldPass().getText();
//				String smtp = getTxtFieldSMTP().getText();
//				String imap = getTxtFieldIMAP().getText();
//				
//				if(account.equalsIgnoreCase("") || user.equalsIgnoreCase("") ||
//						mail.equalsIgnoreCase("") || pass.equalsIgnoreCase("") ||
//						smtp.equals("") || imap.equalsIgnoreCase(""))
//				{
//					JOptionPane.showMessageDialog(window, Strings.AP_saveOptErr[Strings.i]);
//				}
//				else
//				{	// zapisuje nowe konto
//					if(btnSave.getText().equalsIgnoreCase("Dodaj konto"))
//					{
//						PropertiesManager properties = new PropertiesManager(account);
//						
//						boolean exists = properties.create(account, "config.properties"); 
//						
//						if(!exists)
//						{
//							properties.setProperty("account", account);
//							properties.setProperty("user", user);
//							properties.setProperty("mail", mail);
//							properties.setProperty("pass", pass);
//							properties.setProperty("smtp", smtp);
//							properties.setProperty("imap", imap);
//							System.out.println("zaladowano nowe");
//							addProfileToCombo(account);
//							int currSize = MailEngine.getGeneralProperties().getSize();
//							
//							if((currSize) == 0)
//							{
//								System.out.println("ilosc kont == 1");
//								MailEngine.getGeneralProperties().setProperty("account_" + (currSize+1), account);
//								MailEngine.getGeneralProperties().setProperty("current_acc", account);
//								MailEngine.getCurrentProperties().copy(properties);
//								getLblCurrAcc().setText(account);
//							}
//							else
//							{
//								MailEngine.getGeneralProperties().setProperty("account_" + (currSize), account);
//							}
//							
//							MailEngine.getCurrentProperties().show();	
//						}
//						else
//						{
//							System.out.println("istnieje juz takie konto");
//						}
//						
//						// reseting all fields.
//						getTxtFieldAccountName().setText("");
//						getTxtFieldIMAP().setText("");
//						getTxtFieldMail().setText("");
//						getTxtFieldPass().setText("");
//						getTxtFieldSMTP().setText("");
//						getTxtFieldUser().setText("");
//						
//						JOptionPane.showMessageDialog(window, Strings.AP_saveOptInfo[Strings.i] + account, "Info", JOptionPane.INFORMATION_MESSAGE);
//						isComboBoxListenerActive = true;
//					}
//					else
//					{
//						// zapisuje zmiany w istniejacym koncie...
//						System.out.println("\n\n BEFORE EDIT:");
//						getCurrSelProperties().show();
//						
//						getCurrSelProperties().setProperty("user", user);
//						getCurrSelProperties().setProperty("mail", mail);
//						getCurrSelProperties().setProperty("pass", pass);
//						getCurrSelProperties().setProperty("smtp", smtp);
//						getCurrSelProperties().setProperty("imap", imap);
//						System.out.println("\n\n AFTER EDIT:");
//						getCurrSelProperties().show();
//						System.out.println("\n\n\n");
//					}
//				}
//			}
//		});
		
		editAccountPanel.setVisible(false);
		
		
		
		btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMenu.setBounds(10, 425, 70, 23);
		btnMenu.addActionListener(new AccPnlMenuListener(this));
		
		
//		btnMenu.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e)
//			{
//			AccountsPanel.isComboBoxListenerActive = true;	
//				if
//				(
//					(!getTxtFieldAccountName().getText().equalsIgnoreCase("")) || 
//					(!getTxtFieldMail().getText().equalsIgnoreCase("")) ||
//					(!getTxtFieldPass().getText().equalsIgnoreCase("")) ||
//					(!getTxtFieldSMTP().getText().equalsIgnoreCase("")) ||
//					(!getTxtFieldIMAP().getText().equalsIgnoreCase("")) ||
//					(!getTxtFieldUser().getText().equalsIgnoreCase(""))
//				)
//					{
//						int answ = JOptionPane.showConfirmDialog(
//								window, Strings.AP_menu[Strings.i], Strings.AP_menuHed[Strings.i], JOptionPane.YES_NO_OPTION);
//						if(answ == JOptionPane.YES_OPTION)
//						{
//							getTxtFieldAccountName().setText("");
//							getTxtFieldIMAP().setText("");
//							getTxtFieldMail().setText("");
//							getTxtFieldPass().setText("");
//							getTxtFieldSMTP().setText("");
//							getTxtFieldUser().setText("");
//							
//							editAccountPanel.setVisible(false);
//							setVisible(false);
//							window.getHelloPanel().setVisible(true);
//						}
//					}
//					else
//					{
//						window.getInbox().getMessageList().getDLM().removeAllElements();
//						MyMessageHolder.switchInbox();
//						window.getInbox().getMessageList().updateDlm();
//						
//						window.getDraftsPanel().getDraftsList().getDLM().removeAllElements();
//						MyMessageHolder.switchDrafts();
//						window.getDraftsPanel().getDraftsList().updateDlm();
//						window.getDraftsPanel().getPnlNorthDrafts().getNDMCP().updateMessCountLbl();
//
//						window.getSentPanel().getSentList().getDLM().removeAllElements();
//						MyMessageHolder.switchSent();
//						window.getSentPanel().getSentList().updateDlm();
//						window.getSentPanel().getPnlNorthSent().getNSMCP().updateMessCountLbl();
//						
//						
//						
//						editAccountPanel.setVisible(false);
//						setVisible(false);
//						window.getHelloPanel().setVisible(true);
//
//					}
//			}
//		});
		add(btnMenu);
		
		window.add(this);
	}
	
	
	public void loadProfilesToComboBox()
	{
		Iterator<Object>  i =  MailEngine.getGeneralProperties().getProperties().keySet().iterator();
		System.out.println("in load profiels method:");
		MailEngine.getGeneralProperties().show();
		String val = "";
		while(i.hasNext())
		{
			String key = (String) i.next();
			if(key.contains("account"))
			{
				val = MailEngine.getGeneralProperties().getProperty(key);
				System.out.println("k: " + key + "\t v: " + val);
				this.addProfileToCombo(val);
			}
			else
			{
				if(key.equalsIgnoreCase("current_acc"))
				{
					this.lblCurrAccountName.setText(MailEngine.getGeneralProperties().getProperty(key));
				}
			}
		}
	}

	
	
	
	public void addProfileToCombo(String s)
	{
		this.comboBoxAccounts.addItem(s);
	}

	public MainFrame getWindow() {
		return window;
	}

	public JPanel getEditAccountPanel() {
		return editAccountPanel;
	}

	public JTextField getTxtFieldAccountName() {
		return txtFieldAccountName;
	}

	public JTextField getTxtFieldUser() {
		return txtFieldUser;
	}

	public JTextField getTxtFieldMail() {
		return txtFieldMail;
	}

	public JTextField getTxtFieldPass() {
		return txtFieldPass;
	}

	public JTextField getTxtFieldSMTP() {
		return txtFieldSMTP;
	}

	public JTextField getTxtFieldIMAP() {
		return txtFieldIMAP;
	}
	
	public JLabel getLblCurrAcc()
	{
		return this.lblCurrAccountName;
	}

	public PropertiesManager getCurrSelProperties()
	{
		return this.currentlySelectedProperties;
	}
	
	public void setCurrentlySelectedProperties(
			PropertiesManager currentlySelectedProperties) {
		this.currentlySelectedProperties = currentlySelectedProperties;
	}

	public JButton getBtnSave()
	{
		return this.btnSave;
	}
	
	public JButton getBtnDel()
	{
		return this.btnDel;
	}
}

