package com.example.fw;

import java.util.ArrayList;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

public class FolderHelper extends BaseHelper{
	
	protected final JTreeOperator jfolderTree;

	public FolderHelper(ApplicationManager applicationManager) {
		super(applicationManager);
		jfolderTree = new JTreeOperator(mainFrame);
	}

	public Folders getFolders() {
		ArrayList<String> list = new ArrayList<String>();
		
		Object[] children = jfolderTree.getChildren(jfolderTree.getRoot());
		
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

	public String deleteFolder(int index) {
		selectFolderByIndex(index);		
		String folderDeleted = getSelectedFolderName();		
		performFolderDeletion();
		
		return folderDeleted;
	}

	public void deleteFolder(String folder) {
		selectFolderByName(folder);		
		performFolderDeletion();
	}

	private void performFolderDeletion() {
		manager.getMenuHelper().pushDeleteFolder();
		JDialogOperator jdialog = new JDialogOperator(mainFrame);
		new JButtonOperator(jdialog, "Yes").push();
	}
	
	private String getSelectedFolderName() {
		return jfolderTree.getSelectionPath().getLastPathComponent().toString();		
	}

	private void selectFolderByName(String folderName) {
		selectFolderByIndex(jfolderTree.findRow(folderName));
	}
	
	private void selectFolderByIndex(int index) {
		jfolderTree.selectRow(index);
	}
	
}
