package com.model;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ContactsHolder {

	ArrayList<Contact> contactsList = new ArrayList<>();
	final String CURR_PATH;
	
	public ContactsHolder () {
		String path = ContactsHolder.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File f = new File (path);
		CURR_PATH = f.getParent();

		
		//1 i'm loading contacts
		System.out.println("loading to holder...");
		loadContacts();
		if(existsContacts()) {
			System.out.println("current contacts in holder: ");
			for(Contact c : contactsList) {
				System.out.println(c);
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadContacts() 
	{
		if(!existsContacts()) 
		{
			System.out.println("nie istnieja kontakty");
		}
		else 
		{
			try 
			{
				FileInputStream fis = new FileInputStream(CURR_PATH + "\\" + "dat.ser");
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
				contactsList = (ArrayList<Contact>) ois.readObject();
				ois.close();
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void saveContacts() 
	{
		FileOutputStream fout = null;
		
		try 
		{
			fout = new FileOutputStream(CURR_PATH + "\\dat.ser");
			try 
			{
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fout));
				oos.writeObject(contactsList);
				oos.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public boolean existsContacts() 
	{
		boolean exists = false;
		File currentFolder = new File(CURR_PATH);
		for(File f : currentFolder.listFiles()) 
		{
			if(f.getName().equals("dat.ser")) 
			{
				exists = true;
			}
		}
		return exists;
	}
	
	public void show() 
	{
		if(contactsList == null) 
		{
			System.out.println("brak kontaktow");
		} 
		else
		{
			for(Contact c : contactsList) 
			{
				System.out.println("name: " + c.getName());
				System.out.println("sur: " + c.getSurname());
				System.out.println("mail: " + c.getEmail());
				System.out.println("info: " + c.getInfo());
				System.out.println(".................................");
			}
			
		}
	}
	
	public ArrayList<Contact> getContacts() 
	{
		return this.contactsList;
	}
	
	public void setContacts(ArrayList<Contact> list) 
	{
		this.contactsList = list;
	}
	
	public void addContact(Contact c) 
	{
		this.contactsList.add(c);
	}
	
	public void removeContact(Contact c) 
	{
		this.contactsList.remove(c);
	}
}
