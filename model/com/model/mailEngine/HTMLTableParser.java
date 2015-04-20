package com.model.mailEngine;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

class HTMLTableParser extends HTMLEditorKit.ParserCallback {

    private boolean encounteredATableRow = false;
    StringBuilder sb = new StringBuilder("");

    public void handleText(char[] data, int pos) 
    {
        if(encounteredATableRow) 
        {
   //        	System.out.println(new String(data));
        	sb.append(data);
        	sb.append('\n');
        }
    }

    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) 
    {
        if(t == HTML.Tag.TR) encounteredATableRow = true;
    }

    public void handleEndTag(HTML.Tag t, int pos) 
    {
        if(t == HTML.Tag.TR)
        {
        encounteredATableRow = false;
//        System.out.println("^&^");
        sb.append("\n\n");
        }
    }
    
    public StringBuilder getStringBuilder()
    {
    	return sb;
    }
    
    
    
}
