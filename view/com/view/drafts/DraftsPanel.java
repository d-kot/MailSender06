package com.view.drafts;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.model.MessageOut;
import com.view.helloPanel.MainFrame;

public class DraftsPanel extends JPanel{

	
	MainFrame window;
	DraftsMessageList draftsList;
	JSplitPane splitPane;
	JScrollPane scrollPane;
	NorthDraftsPanel pnlNorthDrafts;
	DraftsContentPanel draftsContentPnl;
	
	MessageOut currentDraf;
	
	public DraftsPanel(MainFrame window)
	{
		this.window = window;
		
		this.setLayout(new BorderLayout());

		draftsList = new DraftsMessageList(window, this);
		
		splitPane = new JSplitPane();
		scrollPane = new JScrollPane(draftsList);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		
		splitPane.setTopComponent(scrollPane);
		
		splitPane.setBottomComponent(new JPanel());
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setDividerSize(7);
		this.add(splitPane, BorderLayout.CENTER);
		
		pnlNorthDrafts = new NorthDraftsPanel(this);
		this.add(pnlNorthDrafts, BorderLayout.NORTH);
		this.window.add(this);
		this.splitPane.setDividerLocation(950);

	}
	public MainFrame getWindow() {
		return window;
	}
	public void setWindow(MainFrame window) {
		this.window = window;
	}
	public DraftsMessageList getDraftsList() {
		return draftsList;
	}
	public void setDraftsList(DraftsMessageList draftsList) {
		this.draftsList = draftsList;
	}
	public JSplitPane getSplitPane() {
		return splitPane;
	}
	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public NorthDraftsPanel getPnlNorthDrafts() {
		return pnlNorthDrafts;
	}
	public void setPnlNorthDrafts(NorthDraftsPanel pnlNorthDrafts) {
		this.pnlNorthDrafts = pnlNorthDrafts;
	}
	
	public void setCurrentDraft(MessageOut mOut)
	{
		this.currentDraf = mOut;
	}
	
	public MessageOut getCurrentDraft()
	{
		return this.currentDraf;
	}
	
	
	public DraftsContentPanel getDraftsContentPnl() {
		return draftsContentPnl;
	}
	public void setDraftsContentPnl(DraftsContentPanel draftsContentPnl) {
		this.draftsContentPnl = draftsContentPnl;
	}
	
	
	
}
