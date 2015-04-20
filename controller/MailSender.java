import java.awt.EventQueue;
import java.io.File;

import com.controller.PropertiesController;
import com.model.PropertiesManager;
import com.model.mailEngine.MailEngine;
import com.model.mailEngine.MyMessageHolder;
import com.view.helloPanel.MainFrame;


public class MailSender {
	static String currPath;
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// draw frame

					//creating data folder
					currPath =
							MailSender.class.getProtectionDomain().getCodeSource().
								getLocation().getPath();
					
					File file = new File(currPath);
					final String CURR_PATH = file.getParent() + File.separator + "data";
					
					if(! (new File(CURR_PATH).exists()))
					{
						new File(CURR_PATH).mkdir();
					}
					//cos dodaje.
					
					MailEngine e = new MailEngine();
					
					// load account properties
					MainFrame f = new MainFrame(e);
			//		new PropertiesController(new PropertiesManager(), f);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
