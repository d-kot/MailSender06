package com.model.mailEngine;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.swing.JOptionPane;

import com.model.mailingPanel.Attachment;

/**
 * It's a class that represents "javax.mail.Message"
 * objects in an accessible way. The main culprit of creating
 * this class is the fact that "javax.mail.Message" class doesn't
 * implements Serializable interface, so implementing own class
 * to store data seems to be much better solution. 
 * 
 * @author Damian
 *
 */
public class MyMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	String text;
	ArrayList<Attachment> attachmentsList;
	String sender;
	String reciver;
	String subject;
	String sendDate;
	boolean hasAttachment;
	int zal;
	String unique_id;
	String mimeType;
	boolean isRead;
	long timeInMillis;
	
	public MyMessage(Part bp, Message m, String date, boolean isRead, long timeInMillis)
	{
		System.out.println("inside constructor 1:");
		try {
			System.out.println("From: " + m.getFrom()[0] + " date: " + date);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.timeInMillis = timeInMillis;
		this.isRead = isRead;
		this.attachmentsList = new ArrayList<>();
		setText(bp);
		this.sendDate = date;
		setSubject(m);
		setSender(m);
		setAttachment(m);
		
		MyMessageHolder.addMyMessage(this);
		
		try 
		{
			this.mimeType = m.getContentType();
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public MyMessage(Message m, String date, boolean isRead, long timeInMillis)
	{
		try 
		{
			System.out.println("inside constructor 2:");
			System.out.println("From: " + m.getFrom()[0] + "\n date: " + date);
			System.out.println();
			this.isRead = isRead;
			this.timeInMillis = timeInMillis;
			this.setText(m);
			this.sendDate = date;
			this.subject = m.getSubject();
			this.sender = m.getFrom()[0].toString();
			this.mimeType = m.getContentType();
			MyMessageHolder.addMyMessage(this);
			
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	

	public void setText(Part bp)
	{
		try 
		{
			if(bp.isMimeType("text/html"))
			{
				System.out.println("PARSUJE DO TEKSTU W KONSTRUKTORZE!");
				String t = (MailEngine.parseHTMLToString((String) bp.getContent())).toString();
				
				this.text = t;
			}
			else if(bp.isMimeType("text/plain"))
			{
				this.text =(String) bp.getContent();
				System.out.println("text bez parsowania: " + this.text);
			}
			else
			{
				this.text = ">>>>>>NIEZNANY FORMAT";
			}
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void setText(Message m)
	{	System.out.println("\ninside setText(Message m)");
		try 
		{
			if(m.isMimeType("text/html"))
			{
				System.out.println("PARSUJE DO TEKSTU W KONSTRUKTORZE!");
				
				this.text = (MailEngine.parseHTMLToString((String) m.getContent())).toString();
			}
			else if(m.isMimeType("text/plain"))
			{
				this.text =(String) m.getContent();
			}
			else
			{
				this.text = ">>>>>>NIEZNANY FORMAT :  >> " + m.getContentType();
			}
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void setAttachment(Message m)
	{
		try {
			if(m.isMimeType("multipart/*"))
			{
				Multipart mp = (Multipart) m.getContent();
				for(int i = 0; i < mp.getCount(); i++)
				{
					Part p = mp.getBodyPart(i);
					if(p.getFileName() != null)
					{
						if(p.getDisposition().equalsIgnoreCase("ATTACHMENT"))
						{
							System.out.println("NA PEWNO ZALACZNIK: " + p.getFileName());
//							File f = downloadAttachment(p, m);
//							this.addAttachment(f);
							System.out.println("ROZMIAAR zalacznika: " + p.getSize());
							this.addAttachment(new File(p.getFileName()));
							this.hasAttachment = true;
						}
						else
						{
							this.hasAttachment = false;
						}
					}
				}
			}
		} 
		catch (MessagingException | IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
/*	public static File downloadAtt(Message m, String attFileName)
	{
		File att = null;
		try {
			if(m.isMimeType("multipart/*"))
			{
				Multipart mp = (Multipart) m.getContent();
				for(int i = 0; i < mp.getCount(); i++)
				{
					Part p = mp.getBodyPart(i);
					if(p.getFileName() != null)
					{
						if(p.getDisposition().equalsIgnoreCase("ATTACHMENT"))
						{
							if(p.getFileName().equalsIgnoreCase(attFileName))
							{
								System.out.println("NA PEWNO ZALACZNIK: " + p.getFileName());
								att = downloadAtt(p, m, );
								System.out.println("ROZMIAAR zalacznika: " + p.getSize());
							}
						}
					}
				}
			}
		} 
		catch (MessagingException | IOException e) 
		{
			e.printStackTrace();
		}
		return att;
	}
*/	
	
	public void setSender(Message m) 
	{
		try 
		{
			String sender = (m.getFrom()[0]).toString();

			if(sender.startsWith("=?"))
			{
				int ind = sender.indexOf("<");
				sender = sender.substring(ind+1, sender.lastIndexOf(">"));
			}
			System.out.println("----------- SENDER: " + sender);
			this.sender = sender;
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}

	public void setReciver(String reciver) 
	{
		this.reciver = reciver;
	}
	
	
	public void setSubject(Message m)
	{
		try 
		{
			this.subject = m.getSubject();
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void setSendDate(Message m)
	{
		try 
		{
			this.sendDate = m.getSentDate().toString();
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}

	
	public String toString()
	{
		String zal = "<b> (Z) </b>";
		String s = "";
		if(this.hasAttachment == false)
		{
			if(this.isRead == false)
			{
				s = "<html><b> <font color= \"red\">" + getNbsp(6) + sendDate + getNbsp(3) + 
					sender   +   getNbsp(1) +  "<br>" + getNbsp(6) + "Temat: " + subject   + "</font></b><br>" +
					"_________________________________________________________________________________"
					+ "_______________________________________________________________________________"
					+ "__________________________________________</html>";
			}
			else
			{
				s = "<html>" + getNbsp(6) + "<b> Data: </b>" + sendDate + getNbsp(3) + 
					"<b>Od: </b>" + sender +   getNbsp(1) +  "<br>" + getNbsp(6) + "<b>Temat: </b>" + subject   + "<br>" +
					"_________________________________________________________________________________"
					+ "_______________________________________________________________________________"
					+ "__________________________________________</html>";
			}
		} 
		else
		{
			if(this.isRead == false)
			{
				s = "<html><b><font color= \"red\">"  + zal + getNbsp(1) + sendDate + getNbsp(3) + "<b>Od:</b> " +
					sender   +  "<br>" + getNbsp(6) +  "Temat: " + subject   + "</font><b><br>" +
					"_________________________________________________________________________________"
					+ "________________________________________________________________________________"
					+ "_________________________________________</html>";
			}
			else
			{
				s = "<html>"  + zal + getNbsp(1) + "<b>Data:</b> " + sendDate + getNbsp(3) + 
					"<b>Od:</b> " + sender  + "<br>" +   getNbsp(6) +  "<b>Temat:</b> " + subject   + "<br>" +
					"_________________________________________________________________________________"
					+ "_______________________________________________________________________________"
					+ "__________________________________________</html>";
			}		
		}
		return s;
	}
	
	public String toSimpleString()
	{
		String s = "";
			s = "Data: " + sendDate +"\t" + 
					"From: " + sender  + "\n" +
					"Temat: " + subject + "\n";
	
		return s;
	}
	
	
	private String getNbsp(int amount)
	{
		String s = "";
		for(int i = 0; i< amount; i++)
		{
			s += "&nbsp";
		}
		return s + " ";
	}

	
	private File downloadAttachment(Part bp, Message m)
	{
		File file = null;
		
		
		File allAttachmentsFolder = new File("C:/Users/Damian/Desktop/attach/");
		allAttachmentsFolder.mkdir();
		String path = (this.getSender() + "-" + this.getSendDate());
		
		path = path.replace(':', '-');
		path = path.replace(';', '-');
		path = path.replace('/', '-');
		path = path.replace('\\', '-');
		path = path.replace('<', '-');
		path = path.replace('>', '-');
		
		
		System.out.println("NEW PATH:  "  + path);
		File attFolder = new File(allAttachmentsFolder.getAbsolutePath() + "/"
							+ path);
		attFolder.mkdir();
		
		try 
		{
			file = new File(attFolder.getAbsoluteFile() +"/" + bp.getFileName());
			
			if(file.exists())
			{
				int ans = JOptionPane.showConfirmDialog(null, "Plik " +
						bp.getFileName() + " juz istnieje.\n Podmienic go?");
				if(ans == JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null, "Podmieniono.");
					/**
					 * TODO
					 * a mechanism of overwriting the existing file
					 */
				}
				else
				{
					return null;
				}
			}
			else
			{
		        InputStream in = bp.getInputStream();
				OutputStream out = new FileOutputStream(file);
		        byte[] buf = new byte[1024];
		        int len;
		        while((len=in.read(buf))>0)
		        {
		            out.write(buf,0,len);
		        }
		        out.close();
		        in.close();
			}
	    } 
		catch (Exception e) 
		{
	        e.printStackTrace();
	    } 
		return file;
	}

	
	public static File downloadAtt(Part bp, Message m, Date d)
	{
		File file = null;
		
		
		File allAttachmentsFolder = new File("C:/Users/Damian/Desktop/attach/");
		allAttachmentsFolder.mkdir();
		String path = "";
		
		try 
		{
			System.out.println("downloadAtt method: " + m.getReceivedDate());
			path = (m.getFrom()[0].toString() +" " + d.toString());
		}
		catch (MessagingException e1) 
		{
			e1.printStackTrace();
		}
		
		path = path.replace(':', '-');
		path = path.replace(';', '-');
		path = path.replace('/', '-');
		path = path.replace('\\', '-');
		path = path.replace('<', '(');
		path = path.replace('>', ')');
		
		
		System.out.println("NEW PATH:  "  + path);
		File attFolder = new File(allAttachmentsFolder.getAbsolutePath() + "/"
							+ path);
		attFolder.mkdir();
		
		try 
		{
			file = new File(attFolder.getAbsoluteFile() +"/" + bp.getFileName());
			
			if(file.exists())
			{
				int ans = JOptionPane.showConfirmDialog(null, "Plik " +
						bp.getFileName() + " juz istnieje.\n Podmienic go?");
				if(ans == JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null, "Podmieniono.");
					/**
					 * TODO
					 * a mechanism of overwriting the existing file
					 */
				}
				else
				{
					return null;
				}
			}
			else
			{
		        InputStream in = bp.getInputStream();
				OutputStream out = new FileOutputStream(file);
		        byte[] buf = new byte[4096];
		        int len;
		        while((len=in.read(buf))>0)
		        {
		            out.write(buf,0,len);
		        }
		        out.close();
		        in.close();
			}
	    } 
		catch (Exception e) 
		{
	        e.printStackTrace();
	    } 
		return file;
	}

	
	
	public void addAttachment(File f)
	{
		this.attachmentsList.add(new Attachment(f));
	}
	
	public String getUniqueId()
	{
		return this.unique_id;
	}
	
	public String getText() {
		return text;
	}
	public ArrayList<Attachment> getAttachmentsList() {
		return attachmentsList;
	}
	public String getSender() {
		return sender;
	}
	public String getReciver() {
		return reciver;
	}
	public String getSubject() {
		return subject;
	}
	public String getSendDate() {
		return sendDate;
	}
	
	public int getZal()
	{
		return this.zal;
	}

	
	public void setRead(boolean isRead)
	{
		this.isRead = isRead;
	}
	
	public boolean isRead()
	{
		return this.isRead;
	}
	
	public boolean hasAtt()
	{
		return this.hasAttachment;
	}
	
	public long getTimeInMillis()
	{
		return this.timeInMillis;
	}
	
	public Message getMessage()
	{
		Message m = null;
		
		return m;
	}
}











