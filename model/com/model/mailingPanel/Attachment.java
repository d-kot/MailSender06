package com.model.mailingPanel;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;
	File f;
	
	public Attachment(File f)
	{
		this.f = f;
	}
	
	
	public File getFile(){
		return this.f;
	}
	
	public String toString()
	{
		String s = "";
		try {
			s = "<html> <b>" +  this.f.getName()
				+ "</b><br> "
				+ "<p align=\"left\">"
				+ this.f.getCanonicalPath().toString() +  " </p><br>" +
				"________________________________________________"	 +
				"<br></html>";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	
}
