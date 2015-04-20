package com.model.mailEngine;
import javax.swing.text.html.HTMLEditorKit;

public class HTMLParse extends HTMLEditorKit
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * Call to obtain a HTMLEditorKit.Parser object.
   * 
   * @return A new HTMLEditorKit.Parser object.
   */
  public HTMLEditorKit.Parser getParser()
  {
    return super.getParser();
    
  }

  
}

