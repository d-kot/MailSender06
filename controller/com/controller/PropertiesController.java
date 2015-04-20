package com.controller;

import com.model.PropertiesManager;
import com.view.helloPanel.MainFrame;

public class PropertiesController {

	PropertiesManager propertiesMan;
	MainFrame window;
	
	public PropertiesController(PropertiesManager propertiesMan,
								MainFrame window) {
		this.propertiesMan = propertiesMan;
		this.window = window;
	//	this.window.getMailEnging().setCurrentProperties(propertiesMan);
		
	//	init();
	}
	
//	public void init() {
//		if(propertiesMan.exists())
//		{
//			System.out.println("PLIK config.properties istnieje");
//			propertiesMan.load();
//			window.getAccountPanel().getTextFieldEmail().setText(propertiesMan.getProperty("mail"));
//			window.getAccountPanel().getTextFieldPassword().setText(propertiesMan.getProperty("pass"));
//			window.getAccountPanel().getTextFieldUsername().setText(propertiesMan.getProperty("user"));
//			window.getAccountPanel().getTextFieldSMTP().setText(propertiesMan.getProperty("smtp"));
//			window.setTitle("Hi " + propertiesMan.getProperty("user") + "!");
//			window.setProperties(propertiesMan);
//		} 
//		else 
//		{
//			System.out.println(" PLIK config.properties NIE istnieje");
//			propertiesMan.create();
//			System.out.println("Stworzylem plik config.properties");
//		}
//		
//	}
	
	
}
