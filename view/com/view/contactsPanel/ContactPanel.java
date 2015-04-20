package com.view.contactsPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.model.Contact;
import com.model.Strings;
import com.model.contactsPanel.ContactsAddingListener;
import com.model.contactsPanel.ContactsListRClickListener;
import com.model.mailEngine.MailEngine;
import com.view.helloPanel.MainFrame;

public class ContactPanel extends JPanel{

	

	private static final long serialVersionUID = 1L;
	//fields
	JButton	btnMenu,
			btnAdd,
			btnAnuluj;
	JList <Contact>contactsList;
	JTextField 	txtFieldEmail,
				txtFieldSurname,
				txtFieldName;
	JTextArea	txtAreaVarioiusInfo;
	DefaultListModel<Contact> dlm = new DefaultListModel<>();
	MainFrame window;
	JLabel lblDodajNowyKontakt;
	
	//contructor
	public ContactPanel(MainFrame window) {
		
		this.window = window;
		setBackground(Color.lightGray);
		setVisible(false);
		window.getContentPane().add(this, "name_3698993106685");
		setLayout(null);
		
		btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMenu.setBounds(10, 327, 89, 23);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				window.getHelloPanel().setVisible(true);
				window.setDefaultSize();
				window.setLocationRelativeTo(null);
				if(MailEngine.getCurrentProperties() != null) {
				window.setTitle("Hi " + MailEngine.getCurrentProperties().getProperty("user") + "!");
				} else {
					window.setTitle("Hi!");
				}
			}
		});
		add(btnMenu);
		
		JPanel panel = new JPanel();
		panel.setBounds(290, 30, 234, 315);
		add(panel);
		panel.setLayout(null);
		contactsList = new JList<>(dlm);
		contactsList.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
		contactsList.setBackground(SystemColor.controlHighlight);
		contactsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//ADD A POP UP MENU TO CONTACTS
		
		/*	|----------------|
		 *  |    SEND MAIL   |
		 * 	|----------------|
		 * 	| VIEW FULL INFO |  
		 *  |----------------|
		 *  | 	   EDIT      |
		 *  |----------------|
		 *  |     REMOVE     |
		 *  |----------------|
		 */
		
		contactsList.addMouseListener(new ContactsListRClickListener(this));
		JScrollPane scrollContactsList = new JScrollPane(contactsList);
		scrollContactsList.setBounds(1, 1, 233, 314);
		panel.add(scrollContactsList);
		
		JLabel lblTwojeKontakty = new JLabel(Strings.CP_lblContact[Strings.i]);
		lblTwojeKontakty.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTwojeKontakty.setBounds(290, 5, 83, 23);
		add(lblTwojeKontakty);
		
		lblDodajNowyKontakt = new JLabel(Strings.CP_lblNewContact[Strings.i]);
		lblDodajNowyKontakt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDodajNowyKontakt.setBounds(10, 9, 125, 19);
		add(lblDodajNowyKontakt);
		
		JLabel lblNazwaKontaktu = new JLabel(Strings.CP_lblName[Strings.i]);
		lblNazwaKontaktu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNazwaKontaktu.setBounds(10, 39, 76, 19);
		add(lblNazwaKontaktu);
		
		JLabel lblNazwisko = new JLabel(Strings.CP_lblSurname[Strings.i]);
		lblNazwisko.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNazwisko.setBounds(10, 64, 76, 19);
		add(lblNazwisko);
		
		JLabel lblEmail = new JLabel(Strings.CP_lblMail[Strings.i]);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmail.setBounds(10, 89, 76, 20);
		add(lblEmail);
		
		txtFieldName = new JTextField();
		txtFieldName.setBounds(66, 39, 205, 20);
		add(txtFieldName);
		txtFieldName.setColumns(10);
		
		txtFieldSurname = new JTextField();
		txtFieldSurname.setColumns(10);
		txtFieldSurname.setBounds(66, 64, 205, 20);
		add(txtFieldSurname);

		txtFieldEmail = new JTextField();
		txtFieldEmail.setColumns(10);
		txtFieldEmail.setBounds(66, 89, 205, 20);
		add(txtFieldEmail);
		
		JLabel lblInne = new JLabel(Strings.CP_lblInfo[Strings.i]);
		lblInne.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInne.setBounds(10, 113, 46, 23);
		add(lblInne);
		
		txtAreaVarioiusInfo = new JTextArea();
		txtAreaVarioiusInfo.setLineWrap(true);
		JScrollPane scrPaneVariousInfo = new JScrollPane(txtAreaVarioiusInfo);
		scrPaneVariousInfo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrPaneVariousInfo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPaneVariousInfo.setBounds(10, 135, 261, 91);
		add(scrPaneVariousInfo);
		
		btnAdd = new JButton(Strings.CP_btnAdd[Strings.i]);
		btnAdd.addActionListener(new ContactsAddingListener(this));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAdd.setBounds(182, 237, 89, 23);
		add(btnAdd);

		btnAnuluj = new JButton(Strings.CP_cancel[Strings.i]);
		btnAnuluj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			txtAreaVarioiusInfo.setText("");
			txtFieldEmail.setText("");
			txtFieldName.setText("");
			txtFieldSurname.setText("");
			contactsList.setEnabled(true);
			btnAnuluj.setVisible(false);
			btnAdd.setText(Strings.CP_btnAdd[Strings.i]);
			btnMenu.setEnabled(true);
			}
		});
		btnAnuluj.setBounds(66, 237, 89, 23);
		btnAnuluj.setVisible(false);
		add(btnAnuluj);
	}
	
	public DefaultListModel<Contact> getDefaultListModel() {
		return this.dlm;
	}

	public JButton getBtnMenu() {
		return btnMenu;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JList<Contact> getContactsList() {
		return contactsList;
	}

	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}

	public JTextField getTxtFieldSurname() {
		return txtFieldSurname;
	}

	public JTextField getTxtFieldName() {
		return txtFieldName;
	}

	public JTextArea getTxtAreaVarioiusInfo() {
		return txtAreaVarioiusInfo;
	}

	public DefaultListModel<Contact> getDlm() {
		return dlm;
	}
	
	public MainFrame getWindow() {
		return this.window;
	}

	public JLabel getLblDodajNowyKontatk() {
		return this.lblDodajNowyKontakt;
	}
	
	public JButton getBtnAnuluj() {
		return this.btnAnuluj;
	}
}
