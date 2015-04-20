package com.model.mailEngine;
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
import java.util.Iterator;
import java.util.TreeMap;

import com.model.MessageOut;


/**
 * This class is a repository for Inbox, Drafts and Sent folders.
 *  * It utilizes a TreeMap<long, MyMessage> for Inbox and
 *   TreeMap<Long, MessageOut> for drafts and Sent folder.
 * This class is created as a singleton object whith public getInstance() method.
 * 
 * @see MyMessage
 * @author Damian
 *
 */
public class MyMessageHolder
{
	static TreeMap<Long, MyMessage> inbox;
	static TreeMap<Long, MessageOut> drafts;
	static TreeMap<Long, MessageOut> sent;
	
	static MyMessageHolder holder;
	public static String CURR_PATH;
	public static String PATH_TO_DATA;
	
	private MyMessageHolder()
	{
		String s = MyMessageHolder.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
		File f = new File(s);
		String currAccount = MailEngine.getGeneralProperties().getProperty("current_acc");
		CURR_PATH = f.getParent() + File.separator + "data" +  File.separator + currAccount;
		PATH_TO_DATA = f.getParent() + File.separator + "data";
		inbox = new TreeMap<>();
		drafts = new TreeMap<>();
		sent = new TreeMap<>();
		
		loadInbox();
		
		
		System.out.println("\n\n+++++++ loading drafts...");
		loadDrafts();
		if(drafts != null)
		{
			System.out.println(" ^^^ LICZBA WIADOMOSCI drafts: " + drafts.size());
			
			Iterator <Long> i = drafts.keySet().iterator();
			while(i.hasNext())
			{
				Long key = i.next();
				System.out.println(drafts.get(key).toSimpleString());
			}
			
		}
		System.out.println("+++++++ drafts loaded.\n\n");
		

		
		System.out.println("\n\n+++++++ loading sent...");
		loadSent();
		if(sent != null)
		{
			System.out.println(" ^^^ LICZBA WIADOMOSCI sent: " + sent.size());
			
			Iterator <Long> i = sent.keySet().iterator();
			while(i.hasNext())
			{
				Long key = i.next();
				System.out.println(sent.get(key).toSimpleString());
			}
			
		}
		System.out.println("+++++++ sent loaded.\n\n");

		
	}
	
	public static MyMessageHolder getInstance()
	{	
		if(holder == null)
		{
			return new MyMessageHolder();
		}
		else return holder;
	}
	
	
		/*----------------------------- MESSAGES-----------------------------*/
	
	public static void addMyMessage(MyMessage m)
	{
		if(! (inbox.containsKey(m.getTimeInMillis())))
		{
			inbox.put(m.getTimeInMillis(), m);
			System.out.println("dodano nowa wiadomosc");
		}
		else
		{
		System.out.println("wiadomosc istnieje - NIE DODANO");
		String s = inbox.get(m.getTimeInMillis()).toSimpleString();
		System.out.println("to taka wiadomosc: " + s + "\n\n");
		}
	}
	
	public static void removeMyMessage(MyMessage m)
	{
		inbox.remove(m.getTimeInMillis());
	}
	
	public static TreeMap<Long, MyMessage> getInbox()
	{
		return inbox;
	}
	
	public static void save()
	{
		String s = MyMessageHolder.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
		File f = new File(s);
		String currAccount = MailEngine.getGeneralProperties().getProperty("current_acc");
		String accountPath = f.getParent() + File.separator + "data" +  File.separator + currAccount;
		System.out.println("curr: " + accountPath);
		
		
		
		FileOutputStream fos = null;
		
		if(!(new File(accountPath).exists()))
		{
			System.out.println("curr:  " + accountPath);
			System.out.println("Folder nie istnieje, wiec go tworze");
			new File(accountPath).mkdir();
		}
		else
		{
			System.out.println("folder istnieje");
		}
		
		try 
		{
			fos = new FileOutputStream(accountPath + File.separator + "inbox.ser");

			ObjectOutputStream oos =
					new ObjectOutputStream(new BufferedOutputStream(fos));
			oos.writeObject(inbox);
			oos.close();
			System.out.println("zapisano w : " + accountPath);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadInbox() 
	{
		System.out.println("\n\n loading Inbox...");
		if(!existsFolder("inbox.ser")) 
		{
			System.out.println("nie istnieje inbox");
		}
		else 
		{
			try 
			{
				FileInputStream fis = new FileInputStream(CURR_PATH + File.separator + "inbox.ser");
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
				inbox = (TreeMap<Long, MyMessage>) ois.readObject();
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
		
	// viewing Inbox
		if(inbox != null)
		{
			System.out.println(" ^^^ LICZBA WIADOMOSCI: " + inbox.size());
			
			Iterator <Long> i = inbox.keySet().iterator();
			while(i.hasNext())
			{
				Long key = i.next();
				System.out.println(inbox.get(key).toSimpleString());
			}
			
		}
		System.out.println("+++++++ inbox loaded.\n\n");
	}

	@SuppressWarnings("unchecked")
	public static void switchInbox() 
	{
		System.out.println("inbox size before clear: " + inbox.size());
		inbox.clear();
		System.out.println("inbox after clear: " + inbox.size());
		String s = MyMessageHolder.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
		File f = new File(s);
		String currAccount = MailEngine.getGeneralProperties().getProperty("current_acc");
		String path = f.getParent() + File.separator + "data" +  File.separator + currAccount;
		System.out.println("curr: " + path);
		
		
		if(!existsFolder("inbox.ser")) 
		{
			System.out.println("nie istnieje inbox");
		}
		else 
		{
			try 
			{
				if(new File(path + File.separator + "inbox.ser").exists())
				{
					FileInputStream fis = new FileInputStream(path + File.separator + "inbox.ser");
					ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
					inbox = (TreeMap<Long, MyMessage>) ois.readObject();
					ois.close();
					System.out.println("inbox size after update: " + inbox.size());
				}
				else
				{
					System.out.println("nie istnieje inbox bo zapewne powstalo wlasnie nowe konto.");
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
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}

	
	
	
		/*----------------------------- DRAFTS----------------------------- */
	
	public static void addDraft(MessageOut m)
	{
		if(! (drafts.containsKey(m.getCreationTime())))
		{
			drafts.put(m.getCreationTime(), m);
			System.out.println("dodano drafts: " + m.toSimpleString());
		}
		else
		{
			System.out.println("wiadomoœæ: " + m.toSimpleString() + " istnieje - nie dodano.");
		}
	}
	
	public static void removeDraft(MessageOut m)
	{
		drafts.remove(m.getCreationTime());
		saveDrafts();
	}

	public static TreeMap<Long, MessageOut> getDrafts()
	{
		return drafts;
	}
	
	public static void saveDrafts()
	{
		String s = MyMessageHolder.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
		File f = new File(s);
		String currAccount = MailEngine.getGeneralProperties().getProperty("current_acc");
		String accountPath = f.getParent() + File.separator + "data" +  File.separator + currAccount;
		System.out.println("curr: " + accountPath);
		

		FileOutputStream fos = null;
		
		if(!(new File(accountPath).exists()))
		{
			System.out.println("curr:  " + accountPath);
			System.out.println("Folder nie istnieje, wiec go tworze");
			new File(accountPath).mkdir();
		}
		else
		{
			System.out.println("folder istnieje");
		}
		
		try 
		{
			fos = new FileOutputStream(accountPath + File.separator + "drafts.ser");

			ObjectOutputStream oos =
					new ObjectOutputStream(new BufferedOutputStream(fos));
			oos.writeObject(drafts);
			oos.close();
			System.out.println("zapisano w : " + accountPath);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadDrafts() 
	{
		if(!existsFolder("drafts.ser")) 
		{
			System.out.println("nie istnieje drafts");
		}
		else 
		{
			try 
			{
				FileInputStream fis = new FileInputStream(CURR_PATH + File.separator + "drafts.ser");
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
				drafts = (TreeMap<Long, MessageOut>) ois.readObject();
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

	@SuppressWarnings("unchecked")
	public static void switchDrafts() 
	{
		System.out.println("drafts size before clear: " + drafts.size());
		drafts.clear();
		System.out.println("drafts after clear: " + drafts.size());
		String s = MyMessageHolder.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
		File f = new File(s);
		String currAccount = MailEngine.getGeneralProperties().getProperty("current_acc");
		String path = f.getParent() + File.separator + "data" +  File.separator + currAccount;
		System.out.println("curr: " + path);
		
		
		if(!existsFolder("drafts.ser")) 
		{
			System.out.println("nie istnieje drafts");
		}
		else 
		{
			try 
			{
				if(new File(path + File.separator + "drafts.ser").exists())
				{
					FileInputStream fis = new FileInputStream(path + File.separator + "drafts.ser");
					ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
					drafts = (TreeMap<Long, MessageOut>) ois.readObject();
					ois.close();
					System.out.println("drafts size after update: " + drafts.size());
				}
				else
				{
					System.out.println("nie istnieje drafts bo zapewne powstalo wlasnie nowe konto.");
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
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}

	
	
	
		/*----------------------------- SENT----------------------------- */
	
	public static void addSent(MessageOut m)
	{
			sent.put(m.getCreationTime(), m);
			System.out.println("dodano drafts: " + m.toSimpleString());
	}
	

	public static void removeSent(MessageOut m)
	{
		sent.remove(m.getCreationTime());
		saveSent();
	}
	
	public static TreeMap<Long, MessageOut> getSent()
	{
		return sent;
	}

	public static void saveSent()
	{
		String s = MyMessageHolder.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
		File f = new File(s);
		String currAccount = MailEngine.getGeneralProperties().getProperty("current_acc");
		String accountPath = f.getParent() + File.separator + "data" +  File.separator + currAccount;
		System.out.println("curr: " + accountPath);
		
		
		
		FileOutputStream fos = null;
		
		if(!(new File(accountPath).exists()))
		{
			System.out.println("curr:  " + accountPath);
			System.out.println("Folder nie istnieje, wiec go tworze");
			new File(accountPath).mkdir();
		}
		else
		{
			System.out.println("folder istnieje");
		}
		
		try 
		{
			fos = new FileOutputStream(accountPath + File.separator + "sent.ser");

			ObjectOutputStream oos =
					new ObjectOutputStream(new BufferedOutputStream(fos));
			oos.writeObject(sent);
			oos.close();
			System.out.println("zapisano w : " + accountPath);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadSent() 
	{
		if(!existsFolder("sent.ser")) 
		{
			System.out.println("nie istnieje sent");
		}
		else 
		{
			try 
			{
				FileInputStream fis = new FileInputStream(CURR_PATH + File.separator + "sent.ser");
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
				sent = (TreeMap<Long, MessageOut>) ois.readObject();
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

	@SuppressWarnings("unchecked")
	public static void switchSent() 
	{
		System.out.println("sent size before clear: " + sent.size());
		sent.clear();
		System.out.println("sent after clear: " + sent.size());
		String s = MyMessageHolder.class.getProtectionDomain().
				getCodeSource().getLocation().getPath().toString();
		File f = new File(s);
		String currAccount = MailEngine.getGeneralProperties().getProperty("current_acc");
		String path = f.getParent() + File.separator + "data" +  File.separator + currAccount;
		System.out.println("curr: " + path);
		
		
		if(!existsFolder("sent.ser")) 
		{
			System.out.println("nie istnieje sent");
		}
		else 
		{
			try 
			{
				if(new File(path + File.separator + "sent.ser").exists())
				{
					FileInputStream fis = new FileInputStream(path + File.separator + "sent.ser");
					ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
					sent = (TreeMap<Long, MessageOut>) ois.readObject();
					ois.close();
					System.out.println("sent size after update: " + sent.size());
				}
				else
				{
					System.out.println("nie istnieje sent bo zapewne powstalo wlasnie nowe konto.");
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
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}

	
	
	
	
	
	

	
	public static boolean existsFolder(String folderName) 
	{
		boolean exists = false;
		if(new File(CURR_PATH).exists())
		{
			File currentFolder = new File(CURR_PATH);
			for(File f : currentFolder.listFiles()) 
			{
				if(f.getName().equals(folderName)) 
				{
					exists = true;
				}
			}
		}
		return exists;
	}
	
	public static void show()
	{
		ArrayList<Long> keys = new ArrayList<>(MyMessageHolder.getInbox().keySet());
		System.out.println("\n\n liczba wiadomosci: " + inbox.size());
		for(int i = (keys.size()-1); i >=0; i--)
		{
			MyMessage m = (MyMessageHolder.getInbox().get(keys.get(i)));
			System.out.println(m.toSimpleString());
		}
		System.out.println("-------------------------------------------\n");
	}
	public static int getUnread()
	{
		Iterator<Long> i = inbox.keySet().iterator();
		int unread = 0;
		while(i.hasNext())
		{
			Long key = i.next();
			if(inbox.get(key).isRead == false)
			{
				unread+=1;
			}
		}
		
		System.out.println("--------------UNREAD: " + unread + "   -------------------\n");	
		return unread;
	}
	
	
}