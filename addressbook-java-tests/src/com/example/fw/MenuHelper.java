package com.example.fw;

import org.netbeans.jemmy.operators.JMenuBarOperator;

public class MenuHelper extends BaseHelper{

	public MenuHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void pushCreateFolder() {
		JMenuBarOperator menu = new JMenuBarOperator(mainFrame);
		menu.pushMenuNoBlock("File|New folder...");
		
	}


}
