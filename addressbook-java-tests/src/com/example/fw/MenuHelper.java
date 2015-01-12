package com.example.fw;

import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JMenuOperator;

public class MenuHelper extends BaseHelper{

	public MenuHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void pushCreateFolder() {
		JMenuBarOperator menuBar = new JMenuBarOperator(mainFrame);
		JMenuOperator jMenu = new JMenuOperator(menuBar);
		jMenu.pushMenuNoBlock("File|New folder...");		
	}

	public void pushDeleteFolder() {
		JMenuBarOperator menuBar = new JMenuBarOperator(mainFrame);
		//JMenuOperator jMenu = new JMenuOperator(menuBar);
		menuBar.pushMenuNoBlock("File|Delete");
	}


}
