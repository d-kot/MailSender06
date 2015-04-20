//package com.view.drafts;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JPanel;
//
//import com.model.Strings;
//
//public class NorthCenterDraftsPnl extends JPanel{
//
//	DraftsPanel pnlDrafts;
//	JButton btnMessListOnly;
//	JButton btnDelDraft;
//	JButton btnContinueWriting;
//	JButton btnMainMenu;
//
//	
//	public NorthCenterDraftsPnl(DraftsPanel pnlDrafts)
//	{
//		this.pnlDrafts = pnlDrafts;
//		
//		
//		this.setLayout(new FlowLayout(FlowLayout.CENTER));
//		this.setOpaque(true);
//		this.setBackground(Color.blue);
//		btnMainMenu = new JButton("Menu");
//		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		btnMainMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				pnlDrafts.getPnlNorthDrafts().getNorthWestDraftsPnl().getInfoLabel().setText("");
//			//	pnlDrafts.getDraftsContentPnl().getMessageContentArea().setText("");
//				pnlDrafts.setVisible(false);
//				pnlDrafts.getWindow().setSize(350, 450);
//				pnlDrafts.getWindow().getHelloPanel().setVisible(true);
//			}
//		});
//		this.add(btnMainMenu);
//		
//		btnDelDraft = new JButton("Usuñ kopiê robocz¹");
//		btnDelDraft.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		btnDelDraft.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e)
//			{
//				
//			}
//		});
//		this.add(btnDelDraft);
//														/* UPDATE BUTTON*/
//		btnContinueWriting = new JButton("Kontynuuj edycje");
//		btnContinueWriting.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		
////		btnUpdateNow.addActionListener(new BtnUpdateListener(inbox));
//		
//		this.add(btnContinueWriting);
//			
//		btnMessListOnly = new JButton(Strings.NCIP_btnInboxOnly[Strings.i]);
//		btnMessListOnly.setFont(new Font("Tahoma", Font.PLAIN, 11));
////		btnMessListOnly.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent e)
////			{
////				inbox.getSplitPane().setDividerLocation(700);
////				if(inbox.getMessageContentPanel() != null)
////				{
////				inbox.getMessageContentPanel().setVisible(false);
////				inbox.getSouthPanel().setVisible(false);
////				}
////			}
////		});
////		this.add(btnMessListOnly);
//
//	}
//	
//	
//	public JButton getBtnNewEmail()
//	{
//		return this.btnDelDraft;
//	}
//}




package com.view.drafts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.model.Strings;
import com.model.mailEngine.MyMessageHolder;

public class NorthCenterDraftsPnl extends JPanel{

	private static final long serialVersionUID = 1L;
	DraftsPanel pnlDrafts;
	JButton btnMessListOnly;
	JButton btnDelDraft;
	JButton btnContinueWriting;
	JButton btnMainMenu;

	
	public NorthCenterDraftsPnl(DraftsPanel pnlDrafts)
	{
		this.pnlDrafts = pnlDrafts;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(true);
		this.setBackground(Color.blue);
		btnMainMenu = new JButton("Menu");
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				pnlDrafts.getPnlNorthDrafts().getNorthWestDraftsPnl().getInfoLabel().setText("");
			//	pnlDrafts.getDraftsContentPnl().getMessageContentArea().setText("");
				pnlDrafts.setVisible(false);
				pnlDrafts.getWindow().setDefaultSize();
				pnlDrafts.getWindow().getHelloPanel().setVisible(true);
				pnlDrafts.getWindow().setLocationRelativeTo(null);
			}
		});
		this.add(btnMainMenu);
		
		btnContinueWriting = new JButton("Kontynuuj edycje");
		btnContinueWriting.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnContinueWriting.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				String mes = pnlDrafts.getCurrentDraft().getMesContent();
				String sub = pnlDrafts.getCurrentDraft().getSubject();
				String addr = pnlDrafts.getCurrentDraft().getReceiver();
				
				MyMessageHolder.removeDraft(pnlDrafts.getCurrentDraft());
				pnlDrafts.getDraftsList().updateDlm();
				pnlDrafts.getPnlNorthDrafts().getNDMCP().updateMessCountLbl();
				pnlDrafts.getPnlNorthDrafts().getNorthWestDraftsPnl().getInfoLabel().setText("");
				pnlDrafts.getDraftsContentPnl().getMessageContentArea().setText("");
				pnlDrafts.setVisible(false);
				pnlDrafts.getWindow().getMailingPanel().setVisible(true);
				pnlDrafts.getWindow().getMailingPanel().getTxtFieldSubject().setText(sub);;
				pnlDrafts.getWindow().getMailingPanel().getTxtMessageArea().setText(mes);
				pnlDrafts.getWindow().getMailingPanel().getTxtFieldEmailAdr().setText(addr);
			}
		});
		this.add(btnContinueWriting);

		
		btnDelDraft = new JButton("Usuñ kopiê robocz¹");
		btnDelDraft.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDelDraft.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(pnlDrafts.getCurrentDraft() == null)
				{
					pnlDrafts.getPnlNorthDrafts().getNorthWestDraftsPnl().setInfoLabelNoSelected();
				}
				else
				{
					btnDelDraft.setVisible(false);
					JButton btnYes = new JButton("Usuñ");
					JButton btnNo = new JButton("Anuluj");
					
					btnYes.setFont(new Font("Tahoma", Font.PLAIN, 11));
					btnNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
					
					btnYes.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("usuwam: "+ pnlDrafts.getCurrentDraft().toSimpleString());
							MyMessageHolder.removeDraft(pnlDrafts.getCurrentDraft());
							pnlDrafts.getWindow().getDraftsPanel().getDraftsList().updateDlm();
							btnYes.setVisible(false);
							btnNo.setVisible(false);
							btnDelDraft.setVisible(true);

							pnlDrafts.getPnlNorthDrafts().getNorthWestDraftsPnl().setInfoLabelAfterDel();
							pnlDrafts.getWindow().getDraftsPanel().getPnlNorthDrafts().getNDMCP().updateMessCountLbl();
							pnlDrafts.getDraftsContentPnl().getMessageContentArea().setText("");
							pnlDrafts.setCurrentDraft(null);
						}
					});
					btnNo.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							System.out.println("Anulowano");
							btnYes.setVisible(false);
							btnNo.setVisible(false);
							btnDelDraft.setVisible(true);
						}
					});
					
					add(btnYes);
					add(btnNo);
				}
			}
		});
		this.add(btnDelDraft);
			
		btnMessListOnly = new JButton(Strings.NCIP_btnInboxOnly[Strings.i]);
		btnMessListOnly.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		btnMessListOnly.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e)
//			{
//				inbox.getSplitPane().setDividerLocation(700);
//				if(inbox.getMessageContentPanel() != null)
//				{
//				inbox.getMessageContentPanel().setVisible(false);
//				inbox.getSouthPanel().setVisible(false);
//				}
//			}
//		});
//		this.add(btnMessListOnly);

	}
	
	
	public JButton getBtnDelDraft()
	{
		return this.btnDelDraft;
	}


	public JButton getBtnContinueWriting() {
		return btnContinueWriting;
	}
	
}
