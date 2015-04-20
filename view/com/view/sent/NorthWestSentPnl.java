//package com.view.sent;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//public class NorthWestSentPnl extends JPanel {
//	
//	SentPanel pnlSent;
//	JLabel infoLabel;
//	public NorthWestSentPnl(SentPanel pnlSent)
//	{
//		this.pnlSent = pnlSent;
//		setForeground(Color.GREEN);
//
//		setLayout(new FlowLayout(FlowLayout.LEFT));
//		setOpaque(true);
//		setBackground(Color.BLUE);
//	//	setPreferredSize(new Dimension(400, 40));
//		
//		infoLabel = new JLabel();
//		infoLabel.setForeground(Color.WHITE);
//		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		infoLabel.setPreferredSize(new Dimension(300, 25));
//		add(infoLabel);
//		
//
//	}
//	
//	public JLabel getInfoLabel()
//	{
//		return this.infoLabel;
//	}
//
//	public void setInfoLabelInSouthPnl(String s, String d)
//	{
//		infoLabel.setText("<html>"
//						+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <b>Do:</b> " + s +
//													"<br>" +
//						"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"							+ " <b>Data:</b> " + d +"</html>");
//	}
//
//}



package com.view.sent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NorthWestSentPnl extends JPanel {
	
	SentPanel pnlSent;
	JLabel infoLabel;
	public NorthWestSentPnl(SentPanel pnlSent)
	{
		this.pnlSent = pnlSent;
		setForeground(Color.GREEN);

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(true);
		setBackground(Color.BLUE);
	//	setPreferredSize(new Dimension(400, 40));
		
		infoLabel = new JLabel();
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		infoLabel.setPreferredSize(new Dimension(300, 25));
		add(infoLabel);
		

	}
	
	public JLabel getInfoLabel()
	{
		return this.infoLabel;
	}

	public void setInfoLabelInSouthPnl(String s, String d)
	{
		infoLabel.setText("<html>"
						+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <b>Do:</b> " + s +
													"<br>" +
						"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"							+ " <b>Data:</b> " + d +"</html>");
	}
	
	public void setInfoLabelAfterDel()
	{
		
		infoLabel.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  <b>Usuniêto</b> </html>");
	}
	
	public void setInfoLabelNoSelected()
	{
		
		infoLabel.setText("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp  <b>Nie wybrano ¿adnej wiadomoœci!</b> </html>");
	}

}
