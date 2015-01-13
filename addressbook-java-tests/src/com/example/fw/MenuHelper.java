package com.example.fw;

import org.netbeans.jemmy.operators.JMenuBarOperator;

public class MenuHelper extends BaseHelper{
	
	protected final JMenuBarOperator menuBar;

	public MenuHelper(ApplicationManager applicationManager) {
		super(applicationManager);
		menuBar = new JMenuBarOperator(mainFrame);
	}

	public void pushCreateFolder() {
		menuBar.pushMenuNoBlock("File|New folder...");		
	}

	public void pushDeleteFolder() {
		menuBar.pushMenuNoBlock("File|Delete");
	}


}
