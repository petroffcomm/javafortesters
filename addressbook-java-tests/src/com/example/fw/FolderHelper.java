package com.example.fw;

import java.util.ArrayList;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

public class FolderHelper extends BaseHelper{

	public FolderHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public Folders getFolders() {
		ArrayList<String> list = new ArrayList<String>();
		
		JTreeOperator jtree = new JTreeOperator(mainFrame);
		Object[] children = jtree.getChildren(jtree.getRoot());
		
		for (Object child : children) {
			list.add("" + child);
		}
		return new Folders(list);
	}

	public String createFolder(String folderName) {
		manager.getMenuHelper().pushCreateFolder();
		JDialogOperator jdialog = new JDialogOperator(mainFrame);
		new JTextFieldOperator(jdialog).setText(folderName);
		new JButtonOperator(jdialog, "OK").push();
		return waitMessageDialog("Warning", 3000);
	}
	
}
