package com.model;

import java.io.Serializable;
import java.util.Date;


public class MessageOut implements Serializable{

	String receiver;
	String date;
	String mesContent;
	String subject;
	long creationTime;
	Date dDate;
	public MessageOut(String r,  String s, String c)
	{
		this.receiver = r;
		this.mesContent = c;
		this.subject = s;
		creationTime = System.currentTimeMillis();
		dDate = new Date(creationTime);
		this.date = dDate.toString();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMesContent() {
		return mesContent;
	}

	public void setMesContent(String mesContent) {
		this.mesContent = mesContent;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String toSimpleString()
	{
		return "Data: " + this.date + "\tsub: " + this.subject;
	}
	
	public long getCreationTime()
	{
		return this.creationTime;
	}
	
	
	
	public String toString()
	{
		String s = "<html>" + getNbsp(6) + "<b> Data: </b>" + dDate + getNbsp(3) + 
				"<b>Do: </b>" + receiver +   getNbsp(1) +  "<br>" + getNbsp(6) + "<b>Temat: </b>" + subject   + "<br>" +
				"_________________________________________________________________________________"
				+ "_______________________________________________________________________________"
				+ "__________________________________________</html>";
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
	
}
