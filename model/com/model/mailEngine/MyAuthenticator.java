package com.model.mailEngine;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MyAuthenticator  extends Authenticator
{
	
	 static String USERNAME = "";
	 static String PASSWORD = "";
	
	public MyAuthenticator(String user, String pass) 
	{
		USERNAME = user;
		PASSWORD = pass;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() 
	{
		return new PasswordAuthentication(USERNAME, PASSWORD);
	}
}