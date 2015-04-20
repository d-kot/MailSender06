package com.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.view.helloPanel.MainFrame;

/**
 * This class has a constructor that takes one String argument
 * of value either: 
 * <b>general</b> 
 * or 
 * <b>currentProperties</b> 
 * and thus
 * it creates two files in 
 * <b>/data</b>
 *  folder inside my app.
 * The file 'general' creates a file "config.properties"
 * which holds data of all the accounts that we have plus a key
 * "current_acc" which shows current account.
 * The parameter "currentProperties" creates a file "currPropr.properties"
 * that stores data for the currently active account.
 * @author Damian
 *
 */
public class PropertiesManager
{
	static String currPath =
			PropertiesManager.class.getProtectionDomain().getCodeSource().
				getLocation().getPath();
	
	File f = new File(currPath);
	final String CURR_PATH = f.getParent() + File.separator + "data";
	Properties properties = new Properties();
	FileInputStream input = null;
	FileOutputStream output = null;
	String path;
	String type = "";
	MainFrame window;
	boolean noGeneralProperties = false;
	
	public PropertiesManager()
	{
		
	}
	public PropertiesManager(String type)
	{
		this.type = type;
		
		if(this.type.equalsIgnoreCase("general"))
		{
			if(exists(null, "config.properties"))
			{
				System.out.println("general prop istnieje");
				load(null, "config.properties");
				System.out.println("general prop - zaladowano:");
				this.show();
				System.out.println("--------------\n");
			}
			else
			{
				System.out.println("GENERAL properteis nie istnieje jezcze.");
				create(null, "config.properties");
				System.out.println("general created in: " + CURR_PATH);
			}
		}
		else if(type.equalsIgnoreCase("currentProperties"))
		{
			if(exists(null, "currProp.properties"))
			{
				System.out.println("currProp istnieje");
				load(null, "currProp.properties");
				System.out.println("currPropr - zaladowano:");
				this.show();
				System.out.println("--------------\n");

			}
			else
			{
				create(null, "currProp.properties");
				System.out.println("CURRENT ACCOUNT stworzony w " + CURR_PATH);
			}
		}
	}
	
	/**
	 * This methods loads account specific <b>.properties</b> file
	 * with two arguments:
	 * @param currAccount - is also a name of the folder in which the program
	 * looks for <b> config.properties</b> file
	 * @param propertiesName - this parameter reflects an exact name 
	 * of a <b> .properties </b> file.
	 */
	public void load(String currAccount, String  propertiesName) 
	{
		try 
		{
			if(currAccount == null)
			{
				input = new FileInputStream(CURR_PATH + File.separator + propertiesName);
			}
			else
			{
				input = new FileInputStream(CURR_PATH + File.separator + currAccount + File.separator + propertiesName);
			}
			this.properties.load(input);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * Create function returns true if creating a properties
	 * file was successful, it turns false otherwise.
	 * This boolean value is then used as a conditoin in
	 * an if statemnt preceeding setProperty in AccountsPanel
	 * @param path - folder name that is at the end of the path
	 * and represent the name of the account
	 * @param configFileName
	 * @return
	 */
	public boolean create(String path, String configFileName) 
	{
		boolean exists = false;
		try 
		{
			if(path != null)
			{
				if(new File(CURR_PATH + File.separator + path).exists())
				{
					JOptionPane.showMessageDialog(null, "istnieje taki profil.");
					exists = true;
				}
				else
				{
					System.out.println("nie istniej profil: " +path + "\n");
				File f = new File(CURR_PATH + File.separator + path);
				f.mkdir();
				this.path = f.getAbsolutePath() + File.separator + configFileName;
				output = new FileOutputStream(this.path);
				properties.store(output, null);
				}
			}
			else if(path == null)
			{
				output = new FileOutputStream("data" + File.separator + configFileName);
				properties.store(output, null);
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return exists;
	}
	
	
	public boolean exists(String propertiesFolder, String propertiesName) 
	{
		boolean exists = false;
		File f = null;
		if(propertiesFolder == null)
		{
			f = new File(CURR_PATH);
		}
		else
		{
			f = new File(CURR_PATH + File.separator + propertiesFolder);
		}
		for(File f1 : f.listFiles()) 
		{
			if(f1.getName().equals(propertiesName)) 
			{
				exists = true;
			}
		}
		return exists;
	}

	
//	public void setProperty(String key, String val) 
//	{
//		try 
//		{
//			if(this.type.equalsIgnoreCase("general"))
//			{
//				output = new FileOutputStream("data" + File.separator + "config.properties");
//			}
//			else if(this.type.equalsIgnoreCase("currentProperties"))
//			{
//				
//				output = new FileOutputStream("data" + File.separator + "currProp.properties");
//			}
//			else
//			{
//				output = new FileOutputStream(this.path);
//			}
//			properties.setProperty(key, val);
//			properties.store(output, null);
//		} 
//		catch (FileNotFoundException e) 
//		{
//			e.printStackTrace();
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
//	}
	

	
	
	
	public void setProperty(String key, String val) 
	{
		try 
		{
			if(this.type.equalsIgnoreCase("general"))
			{
				output = new FileOutputStream("data" + File.separator + "config.properties");
			}
			else if(this.type.equalsIgnoreCase("currentProperties"))
			{
				
				output = new FileOutputStream("data" + File.separator + "currProp.properties");
			}
			else
			{	// tu jest zmiana
				output = new FileOutputStream("data" + File.separator + this.type + File.separator + "config.properties");
			}
			properties.setProperty(key, val);
			properties.store(output, null);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public String getProperty(String key) 
	{
		String val = properties.getProperty(key);
		System.out.println("key: " + key + "  val: " + val);
		return val;
	}

	
	public int getSize()
	{
		return this.properties.size();
	}
	
	
	
	public void copy(PropertiesManager m)
	{	System.out.println("\nCopying " + m.type + " to " + this.type + "... ");
		Iterator<Object>i =  m.properties.keySet().iterator();
		System.out.println( "\n" + this.type + ":");
		while(i.hasNext())
		{
			String o =  (String) i.next();
			String val = m.properties.getProperty(o);
			this.setProperty(o, val);
			System.out.println(">key:" + o + "\tVal:" + this.getProperty(o));
		}
	}
	
	
	public void show()
	{
		Iterator<Object>i =  this.getProperties().keySet().iterator();
		System.out.println( "\n" + this.type + ":");
		while(i.hasNext())
		{
			String o =  (String) i.next();
			String val = this.properties.getProperty(o);
			System.out.println("key: " + o + "\t val: " + val);
		}
	}
	
	
	public Properties getProperties()
	{
		return this.properties;
	}
}
