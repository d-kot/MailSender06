//package com.view.sent;
//
//import java.awt.Color;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//import com.model.Strings;
//import com.model.mailEngine.MyMessageHolder;
//
//public class NorthCenterSentPnl extends JPanel{
//
//	SentPanel pnlSent;
//	JButton btnMessListOnly;
//	JButton btnDelSent;
//	JButton btnForwardMess;
//	JButton btnMainMenu;
//	JLabel lblDelInfo = null;
//	
//	public NorthCenterSentPnl(SentPanel pnlSent)
//	{
//		this.pnlSent = pnlSent;
//		
//		
//		this.setLayout(new FlowLayout(FlowLayout.CENTER));
//		this.setOpaque(true);
//		this.setBackground(Color.blue);
//		btnMainMenu = new JButton("Menu");
//		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		btnMainMenu.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				if(lblDelInfo != null)
//				{
//					lblDelInfo.setVisible(false);
//				}
//				
//				pnlSent.getPnlNorthSent().getNorthWestSentPnl().getInfoLabel().setText("");
//				if(pnlSent.getSentContentPnl() != null)
//				{
//					pnlSent.getSentContentPnl().getMessageContentArea().setText("");
//				}
//				pnlSent.setVisible(false);
//				pnlSent.getWindow().setSize(350, 450);
//				pnlSent.getWindow().getHelloPanel().setVisible(true);
//			}
//		});
//		this.add(btnMainMenu);
//		
//		btnForwardMess = new JButton("Przeka¿ dalej");
//		btnForwardMess.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		btnForwardMess.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e)
//			{
//				String content = pnlSent.getCurrentSent().getMesContent();
//				String sub = pnlSent.getCurrentSent().getSubject();
//				pnlSent.setVisible(false);
//				pnlSent.getWindow().getMailingPanel().setVisible(true);
//				pnlSent.getWindow().getMailingPanel().getTxtFieldSubject().setText(sub);
//				pnlSent.getWindow().getMailingPanel().getTxtMessageArea().setText(content);
//			}
//		});
//		this.add(btnForwardMess);
//		
//		btnDelSent = new JButton("Usuñ z wys³anych");
//		btnDelSent.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		btnDelSent.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				btnDelSent.setVisible(false);
//				JButton btnYes = new JButton("Usuñ");
//				JButton btnNo = new JButton("Anuluj");
//				if(lblDelInfo != null)
//				{
//					lblDelInfo.setVisible(false);
//				}
//				btnYes.setFont(new Font("Tahoma", Font.PLAIN, 11));
//				btnNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
//				
//				btnYes.addActionListener(new ActionListener() 
//				{
//					public void actionPerformed(ActionEvent e)
//					{
//						System.out.println("usuwam: "+ pnlSent.getCurrentSent().toSimpleString());
//						MyMessageHolder.removeSent(pnlSent.getCurrentSent());
//						pnlSent.getWindow().getSentPanel().getSentList().updateDlm();
//						btnYes.setVisible(false);
//						btnNo.setVisible(false);
//						btnDelSent.setVisible(true);
//						lblDelInfo = new JLabel("Usuniêto");
//						lblDelInfo.setOpaque(true);
//						lblDelInfo.setBackground(Color.blue);
//						lblDelInfo.setForeground(Color.white);
//						
//						add(lblDelInfo);
//						pnlSent.getWindow().getSentPanel().getPnlNorthSent().getNSMCP().updateMessCountLbl();
//						pnlSent.getSentContentPnl().getMessageContentArea().setText("");
//						pnlSent.getPnlNorthSent().getNorthWestSentPnl().getInfoLabel().setText("");
//					}
//				});
//				btnNo.addActionListener(new ActionListener() 
//				{
//					public void actionPerformed(ActionEvent e)
//					{
//						System.out.println("Anulowano");
//						btnYes.setVisible(false);
//						btnNo.setVisible(false);
//						btnDelSent.setVisible(true);
//					}
//				});
//				add(btnYes);
//				add(btnNo);
//			}
//		});
//
//		this.add(btnDelSent);
//														/* UPDATE BUTTON*/
//
//			
//		btnMessListOnly = new JButton(Strings.NCIP_btnInboxOnly[Strings.i]);
//		btnMessListOnly.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		btnMessListOnly.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				pnlSent.getSplitPane().setDividerLocation(700);
//				if(pnlSent.getSentContentPnl() != null)
//				{
//				pnlSent.getSentContentPnl().setVisible(false);
//				}
//			}
//		});
//	//	this.add(btnMessListOnly);
//
//	}
//	
//	
//	public JButton getBtnNewEmail()
//	{
//		return this.btnDelSent;
//	}
//	
//	public JLabel getLblDelInfo()
//	{
//		return this.lblDelInfo;
//	}
//
//}






package com.view.sent;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Strings;
import com.model.mailEngine.MyMessageHolder;

public class NorthCenterSentPnl extends JPanel{

	SentPanel pnlSent;
	JButton btnMessListOnly;
	JButton btnDelSent;
	JButton btnForwardMess;
	JButton btnMainMenu;
	
	public NorthCenterSentPnl(SentPanel pnlSent)
	{
		this.pnlSent = pnlSent;
		
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(true);
		this.setBackground(Color.blue);
		btnMainMenu = new JButton("Menu");
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMainMenu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				pnlSent.getPnlNorthSent().getNorthWestSentPnl().getInfoLabel().setText("");
				if(pnlSent.getSentContentPnl() != null)
				{
					pnlSent.getSentContentPnl().getMessageContentArea().setText("");
				}
				pnlSent.setVisible(false);
				pnlSent.getWindow().setDefaultSize();
				pnlSent.getWindow().getHelloPanel().setVisible(true);
				pnlSent.getWindow().setLocationRelativeTo(null);
			}
		});
		this.add(btnMainMenu);
		
		btnForwardMess = new JButton("Przeka¿ dalej");
		btnForwardMess.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnForwardMess.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(pnlSent.getCurrentSent() == null)
				{
					pnlSent.getPnlNorthSent().getNorthWestSentPnl().setInfoLabelNoSelected();
				}
				else
				{
					String content = pnlSent.getCurrentSent().getMesContent();
					String sub = pnlSent.getCurrentSent().getSubject();
					pnlSent.setVisible(false);
					pnlSent.getWindow().getMailingPanel().setVisible(true);
					pnlSent.getWindow().getMailingPanel().getTxtFieldSubject().setText(sub);
					pnlSent.getWindow().getMailingPanel().getTxtMessageArea().setText(content);
				}
			}
		});
		this.add(btnForwardMess);
		
		btnDelSent = new JButton("Usuñ z wys³anych");
		btnDelSent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDelSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(pnlSent.getCurrentSent() == null)
				{
					pnlSent.getPnlNorthSent().getNorthWestSentPnl().setInfoLabelNoSelected();
				}
				else
				{
					btnDelSent.setVisible(false);
					JButton btnYes = new JButton("Usuñ");
					JButton btnNo = new JButton("Anuluj");

					btnYes.setFont(new Font("Tahoma", Font.PLAIN, 11));
					btnNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
					
					btnYes.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("usuwam: "+ pnlSent.getCurrentSent().toSimpleString());
							MyMessageHolder.removeSent(pnlSent.getCurrentSent());
							pnlSent.getWindow().getSentPanel().getSentList().updateDlm();
							btnYes.setVisible(false);
							btnNo.setVisible(false);
							btnDelSent.setVisible(true);

							pnlSent.getWindow().getSentPanel().getPnlNorthSent().getNSMCP().updateMessCountLbl();
							pnlSent.getSentContentPnl().getMessageContentArea().setText("");
							pnlSent.getPnlNorthSent().getNorthWestSentPnl().setInfoLabelAfterDel();
							pnlSent.setCurrentSent(null);
						}
					});
					btnNo.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("Anulowano");
							btnYes.setVisible(false);
							btnNo.setVisible(false);
							btnDelSent.setVisible(true);
						}
					});
					add(btnYes);
					add(btnNo);
				}
			}
		});

		this.add(btnDelSent);
														/* UPDATE BUTTON*/

			
		btnMessListOnly = new JButton(Strings.NCIP_btnInboxOnly[Strings.i]);
		btnMessListOnly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMessListOnly.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				pnlSent.getSplitPane().setDividerLocation(700);
				if(pnlSent.getSentContentPnl() != null)
				{
				pnlSent.getSentContentPnl().setVisible(false);
				}
			}
		});
	//	this.add(btnMessListOnly);

	}
	
	
	public JButton getBtnNewEmail()
	{
		return this.btnDelSent;
	}
	

}
