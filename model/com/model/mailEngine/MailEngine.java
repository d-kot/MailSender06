package com.model.mailEngine;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.util.SharedByteArrayInputStream;
import javax.swing.text.html.HTMLEditorKit;

import com.model.PropertiesManager;
import com.model.inbox.DownAttListener;
import com.model.mailingPanel.Attachment;
import com.view.inbox.DownAttFrame;
import com.view.mailingPanel.MailingPanel;

/**
 * Main mail engine class.
 * @author Damian
 *
 */
public class MailEngine
{
	
//	final static String EMAIL_ADDR = "damian_kot@hotmail.com";
//	final static String PASSWORD = "L14079300";
//	final static String SMTP_SERVER = "smtp.live.com";
//	final static String IMAP = "imap-mail.outlook.com";

//	final static String EMAIL_ADDR = "damkot020590@gmail.com";
//	final static String PASSWORD = "mistrz18";
//	final static String SMTP_SERVER = "smtp.gmail.com";
//	final static String IMAP = "imap.gmail.com";
	
//	final static String EMAIL_ADDR = "damkot0205@interia.pl";
//	final static String PASSWORD = "mistrz1234";
//	final static String SMTP_SERVER = "poczta.interia.pl";
//	final static String IMAP = "poczta.interia.pl";

//	final static String EMAIL_ADDR = "s10923@pjwstk.edu.pl";
//	final static String PASSWORD = "yY{23kK}";
//	final static String SMTP_SERVER = "smtp.gmail.com";
//	final static String IMAP = "imap.gmail.com";
	

//	 static String EMAIL_ADDR;
//	 static String PASSWORD;
//	 static String SMTP_SERVER;
//	 static String IMAP;
	
	static Session session1 = null;
	static Properties props = null;
	static MyMessageHolder messageHolder = null;
	static PropertiesManager generalProperties, currentProperties;
	
	public MailEngine()
	{
		generalProperties = new PropertiesManager("general");
		currentProperties = new PropertiesManager("currentProperties");
		
//		EMAIL_ADDR = currentProperties.getProperty("mail");
//		PASSWORD = currentProperties.getProperty("pass");
//		SMTP_SERVER = currentProperties.getProperty("smtp");
//		IMAP = currentProperties.getProperty("imap");
		
//		if(SMTP_SERVER != null)
//		{
//			props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", SMTP_SERVER);
//			props.put("mail.smtp.port", "587");
//			props.put("mail.store.protocol", "imaps");
//			
//			getNewSession();
//		}
		
		if(MailEngine.getCurrentProperties().getProperties().containsKey("account"))
		{
			init();
		}
		
		messageHolder = MyMessageHolder.getInstance();

		
		System.out.println("^^^ MailEngine created.");
	}

	
	public static void init()
	{
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", MailEngine.getCurrentProperties().getProperty("smtp"));
		props.put("mail.smtp.port", "587");
		props.put("mail.store.protocol", "imaps");
		props.put("mail.imaps.fetchsize","22020096");

		getNewSession();

	}
	
	public static void getNewSession()
	{
		
		session1 = null;
		session1 =  Session.getInstance(props, new MyAuthenticator(MailEngine.getCurrentProperties().getProperty("mail"),
				MailEngine.getCurrentProperties().getProperty("pass")));
		
	}
	
	
	/**
	 * This method is used by  'DownAttListener' of 'DownAttFrame' and is
	 * used to find Message from user's inbox according to the first
	 * argument's field (receivedDateInMillis). The second argument is
	 * used only to be passed to the next method 'readMessToDownloadAtt' 
	 * of the MailEngine class.
	 * @param messWithAtt
	 * @param attFileName
	 * @see DownAttFrame
	 * @see DownAttListener
	 */
	public void readToDownloadAtt(MyMessage messWithAtt, String attFileName)
	{
		try 
		{
			Store store = session1.getStore();
			store.connect(MailEngine.getCurrentProperties().getProperty("imap"), 
						MailEngine.getCurrentProperties().getProperty("mail"),
						MailEngine.getCurrentProperties().getProperty("pass"));
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);

			Message [] mesTab = inbox.search(getPeriodOfMessWithAtt(messWithAtt));
			
			Message searchedMessWithAtt = null;
			Date d1 = new Date(messWithAtt.getTimeInMillis());
			System.out.println("date of parameter mess: " + d1.toString());
			
			for(int i = 0; i < mesTab.length; i++)
			{	
				System.out.println("searched params: " + mesTab[i].getReceivedDate().toString());
				
				if(mesTab[i].getReceivedDate().equals(d1))
				{
					searchedMessWithAtt = mesTab[i];
					System.out.println("znaleziono szukana wiadomosc!");
					break;
				}
			}
			
			try 
            {
            	System.out.println("\t 'readMethod' main flow.");
            	readMessToDownloadAtt(searchedMessWithAtt.getContent(), searchedMessWithAtt, attFileName,  d1); /* read mess! <<<<<<< */
            }
            catch (MessagingException e)
            {
            	System.out.println("\t 'readMethod' alternative flow.");	            	
               System.out.println("alternative flow and mess nr: " + searchedMessWithAtt.getMessageNumber() + "\n");
            	MimeMessage msg = (MimeMessage)inbox.getMessage(searchedMessWithAtt.getMessageNumber()); /* tu moze byc blad...*/
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                msg.writeTo(bos);
                bos.close();
                SharedByteArrayInputStream bis =
            		    new SharedByteArrayInputStream(bos.toByteArray());
                MimeMessage cmsg = new MimeMessage(session1, bis);
                bis.close();
                readMessToDownloadAtt(cmsg.getContent(), cmsg, attFileName, d1); /* read mess! <<<<<<< */
            }
		} 
		catch (NoSuchProviderException e) 
		{
			e.printStackTrace();
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		 System.out.println("\n>>>>>>>>>> SUCCESSFULLY LOADED MESSAGES<<<<<<<<<<<<<\n");
	}

	
	
	/**
	 * This method is used by update inbox method by
	 * the 'BtnUpdateListener' and is used to update
	 * inbox by messages from scope between now and the 
	 * number of days provided in the paramter.
	 * @param daysBackFromToday
	 */
	public void read(int daysBackFromToday)
	{
		try 
		{
			Store store = session1.getStore();
//			store.connect(IMAP, EMAIL_ADDR, PASSWORD);
			store.connect(MailEngine.getCurrentProperties().getProperty("imap"), 
					MailEngine.getCurrentProperties().getProperty("mail"),
					MailEngine.getCurrentProperties().getProperty("pass"));

			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			System.out.println("nr of UNREAD mess: " + inbox.getUnreadMessageCount());
			System.out.println("nr of all mess: " + inbox.getMessageCount() + "\n\n\n\n\n");
			
			Message [] mesTab = inbox.search(getPeriodFrom(daysBackFromToday));
			
			Message m = null;
			for(int i = 0; i < mesTab.length; i++)
			{
				m = mesTab[i];
				System.out.println("\n\n MESS: \n" + m.getReceivedDate()  + 
						"\nFrom: " + m.getFrom()[0].toString() +
						"\nSub: " + m.getSubject()+ "\n ==========");

				String date =  m.getReceivedDate().toString();
				Date d = m.getReceivedDate();
				long time = d.getTime();

			
				
				boolean isRead = m.isSet(Flag.SEEN);

				try 
	            {
	            	System.out.println("\t 'readMethod' main flow.");
	            	System.out.println("before readMess:\n" + m.getReceivedDate() + 
	            			"\nFrom: " + m.getFrom()[0]);
	            	readMess(m.getContent(), m, date, isRead, time); /* read mess! <<<<<<< */
	            	System.out.println("after readMess:\n" + m.getReceivedDate() + 
	            			"\nFrom: " + m.getFrom()[0] + "\n");
	            }
	            catch (MessagingException e)
	            {
	                ByteArrayOutputStream bos = new ByteArrayOutputStream();
	                m.writeTo(bos);
	                bos.close();
	                SharedByteArrayInputStream bis =
        		    new SharedByteArrayInputStream(bos.toByteArray());
		            MimeMessage cmsg = new MimeMessage(session1, bis);
		            bis.close();
		            
		            
		            System.out.println("\n\n \tcmsg message");
		            System.out.println(cmsg.getReceivedDate() + "\nFrom: " + cmsg.getFrom()[0]);
	            	System.out.println("$$$$$$$$$$$$$$$\n");
	                readMess(cmsg.getContent(), cmsg, date, isRead, time); /* read mess! <<<<<<< */
         	
	                
	                /* the piece of code below was marked commented on 20th of March
	                 * because of a problem which was that some of the messages were
	                 * ommited and weren't added to the inbox. The bug was was caused
	                 * by my overlook, namely some time before I changed key in my 
	                 * MessageHolder into long value representing vale of getReceivedDate()
	                 * in milliseconds which is 'pretty' unique val. Unfortunatelly I
	                 * added this field only to one of the two constructors and to the method
	                 * of adding new messages. That caused the problem that the second constructor
	                 * didn't work properly. SOLVED
	                 * ps.
	                 * the code above was written by me as a substitue of the original code
	                 * (the one below) taken from Oracle like sources. */
	                
//	            	System.out.println("\t 'readMethod' alternative flow.");	            	
//	                MimeMessage msg = (MimeMessage)inbox.getMessage(i+1);
//
//	                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//	                msg.writeTo(bos);
//	                bos.close();
//	                SharedByteArrayInputStream bis =
//	            		    new SharedByteArrayInputStream(bos.toByteArray());
//	                MimeMessage cmsg = new MimeMessage(session1, bis);
//	                bis.close();
////	                System.out.println("TYPE OF CONTENT: " + cmsg.getContentType());
//	              //  System.out.println( " ***** SENT DATE: " +cmsg.getReceivedDate().toString());
//	            	System.out.println("\t MSGbefore readMethod:\n" + msg.getReceivedDate() + 
//	            			"\nFrom: " + msg.getFrom()[0] + "\n");
//
//	            	System.out.println("\t CMSG before readMethod:\n" + msg.getReceivedDate() + 
//	            			"\nFrom: " + msg.getFrom()[0] + "\n");
//
//	            	
//	                readMess(cmsg.getContent(), cmsg, date, isRead, time); /* read mess! <<<<<<< */

	            }
				
				
				System.out.println("\n current INBOX:");
				MyMessageHolder.show();
				
				
				
				System.out.println("\t\t %%%%%%%%%%%% END OF MESS %%%%%%%%%%%");
			}
		} 
		catch (NoSuchProviderException e) 
		{
			e.printStackTrace();
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		 System.out.println(">>>>>>>>>> SUCCESSFULLY LOADED MESSAGES");
	}
	

	/**
	 * This method finds a Part object which has to be
	 * an attachment looked for by the user. The core
	 * downaload happens in the subsequent static function
	 * 'downloadAtt(BodyPart, Message m, Date d):File' of the MyMessage class.
	 * @param message
	 * @param m
	 * @param attFileName
	 * @throws Exception
	 * @see MyMessage
	 * @see DownAttFrame
	 * @see DownAttListener
	 */
	private void readMessToDownloadAtt(Object message, Message m, String attFileName, Date d) throws Exception
	{
		if(message instanceof Multipart)
		{
			Multipart mp = (Multipart) message;
			
			for(int i = 0; i < mp.getCount(); i++)
			{
				Part bp = mp.getBodyPart(i);
				
				if(bp.isMimeType("multipart/*"))
				{
					readMessToDownloadAtt( bp.getContent(), m, attFileName, d);
				} // additional condition below prevents from displaying .txt attachments as message text
				if (bp.getFileName() != null /**|| (bp.isMimeType("text/html"))*/)
				{
					// tylko tu moze byc zalacznik
					
					if(bp.getFileName().equals(attFileName))
					{
						System.out.println("*******" + bp.getFileName()+ " <<< to bedzie do pobrania.");
		//				System.out.println("z wiadomosci: " + m.getReceivedDate() + " from: " + m.getFrom()[0]);
		//				File att = MyMessage.downloadAtt(m, attFileName); 
						File f = MyMessage.downloadAtt(bp, m, d);
						/* two lines above differ as follow: the upper one uses one more method, 
						 * whereas the bottom one is direct for 20.03.2015 0:51 the latter works great*/
						System.out.println(">>>> pobrano: " + f.getName());
					}
						/* to dziala */
				}
			}
		}
	}

	
	/**
	 * This method is used by read(int nrOfDays) method used during updating
	 * the inbox by 'BtnUpdateListner'.
	 * @param message
	 * @param m
	 * @param date
	 * @param isRead
	 * @param timeInMillis
	 * @throws Exception
	 */
	private void readMess(Object message, Message m, String date, boolean isRead, long timeInMillis) throws Exception
	{
		if(message instanceof Multipart)
		{
			System.out.println("inside readMess multipart:");
        	System.out.println("before readMess:\n" + m.getReceivedDate() + 
        			"\nFrom: " + m.getFrom()[0] + "\n");

			Multipart mp = (Multipart) message;
			
			for(int i = 0; i < mp.getCount(); i++)
			{
				Part bp = mp.getBodyPart(i);
				
				if(bp.isMimeType("multipart/*"))
				{
					readMess( bp.getContent(), m, date, isRead, timeInMillis);
				} // additional condition below prevents from displaying .txt attachments as message text
				else if (bp.isMimeType("text/plain") && bp.getFileName() == null /**|| (bp.isMimeType("text/html"))*/)
				{
					System.out.println("\n wiadomosc przed konstruktorem to text/plain");
					new MyMessage(bp, m, date, isRead, timeInMillis);
				}
			}
		}
		else if(message instanceof String)
		{	
			System.out.println("\nwiadomosc przed konstruktorem to String: ");
			System.out.println("inside readMess multipart:");
			System.out.println("rozmiar tablicy adresatow: " + m.getFrom().length);
        	System.out.println("before readMess:\n" + m.getReceivedDate() + 
        			"\nFrom: " + m.getFrom()[0] + "\n");


			new MyMessage(m, date, isRead, timeInMillis);
		}
	}
	
	
	public static StringBuilder parseHTMLToString(String s)
	{
		System.out.println("message STRING: ");
		StringReader r = new StringReader(s);
		HTMLEditorKit.Parser parser = new HTMLParse().getParser();
		HTMLTableParser htmlParser = new HTMLTableParser();
		try
		{
			parser.parse(r, htmlParser,true);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println(htmlParser.getStringBuilder().toString());
		System.out.println("-----STRING BUILDER:");
		return htmlParser.getStringBuilder();
	}
	
	
	/**
	 * This method sends E-mails without attachments.
	 * @see MailingPanel
	 * @param user
	 * @param rec
	 * @param sub
	 * @param mess
	 */
	public boolean sendMail(String user, String rec, String sub, String mess) 
	{
		boolean isSent = false;
		try 
		{	 
			Message message = new MimeMessage(session1);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO,
									InternetAddress.parse(rec));
			message.setSubject(sub);
			
			MimeBodyPart mimeTextPart = new MimeBodyPart();
			mimeTextPart.setText(mess);
			
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(mimeTextPart);
			message.setContent(multiPart);
			
			Transport.send(message);
 
			System.out.println("Done");
			isSent = true;
		} 
		catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
		return isSent;
	}	


	/**
	 * This method sends E-mails with attachemtns of type Attachment.
	 * @see Attachment
	 * @see MailingPanel
	 * @param user
	 * @param rec
	 * @param sub
	 * @param mess
	 * @param attachments
	 */
	public boolean sendMail(String user, String rec, String sub, String mess, ArrayList<Attachment> attachments) 
	{
		boolean isSent = false;
		try 
		{	 
			Message message = new MimeMessage(session1);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO,
									InternetAddress.parse(rec));
			message.setSubject(sub);

			MimeBodyPart mimeTextPart = new MimeBodyPart();
			mimeTextPart.setText(mess);

			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(mimeTextPart);
			
			for(Attachment att : attachments)
			{
				DataSource ds = new FileDataSource(att.getFile().getAbsolutePath());
				MimeBodyPart mimeAttachment = new MimeBodyPart();
				mimeAttachment.setDataHandler(new DataHandler(ds));
				mimeAttachment.setFileName(att.getFile().getName());
				multiPart.addBodyPart(mimeAttachment);
			}
			
			message.setContent(multiPart);
			
			Transport.send(message);
 
			System.out.println("Done");
			isSent = true;
		} 
		catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		} 		
		return false;
	}	
	
	
	/**
	 * This method returns 'ReceivedDateTerm' object which is an argument
	 * to 'search()' method of the 'Inbox' object. It uses IMAP protocol
	 * which means that messages are selected BEFORE downloading them to server.
	 * 
	 * Since 'Date' object has only one currently valid contructor 'new Date(long)'
	 * this method creates a 'Calendar' object, then takes 'daysBackToNow' parameter
	 * and add minus sign to it. It means that I'm subtracting this amount of days
	 * from current day. 
	 * Then I pass this date as long via 'getTimeInMillis()' to Date constructor.
	 * @param daysBackToNow
	 * @return
	 */
	public ReceivedDateTerm getPeriodFrom(int daysBackToNow)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, - (daysBackToNow));

		ReceivedDateTerm rdt = new ReceivedDateTerm(ReceivedDateTerm.GT, new Date(cal.getTimeInMillis()));
		
		return rdt;
	}
	
	
	public ReceivedDateTerm getPeriodOfMessWithAtt(MyMessage m)
	{
		ReceivedDateTerm rdt = new ReceivedDateTerm(ReceivedDateTerm.GT, new Date(m.getTimeInMillis()));

		return rdt;
	}
	
	public static Properties getMailingProperties()
	{
		return props;
	}
	
	public static Session getSession()
	{
		return session1;
	}
	
	public MyMessageHolder getMyMessageHolder()
	{
		return messageHolder;
	}
	
	public static PropertiesManager getCurrentProperties()
	{
		return currentProperties;
	}
	
	public static PropertiesManager getGeneralProperties()
	{
		return generalProperties;
	}
}



