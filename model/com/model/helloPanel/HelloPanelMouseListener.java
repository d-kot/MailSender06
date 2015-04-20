package com.model.helloPanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class HelloPanelMouseListener implements MouseListener{
	
	JButton b;
public HelloPanelMouseListener(JButton b)
{
	this.b = b;
}	

		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {
			b.setBackground(new Color(153,204, 255));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			b.setBackground(Color.LIGHT_GRAY);
		}

		public void mousePressed(MouseEvent arg0) {
			
		}

		public void mouseReleased(MouseEvent arg0) {
		}
		
}
