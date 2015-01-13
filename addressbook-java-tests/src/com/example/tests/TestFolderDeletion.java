package com.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.example.fw.Folders;

public class TestFolderDeletion extends TestBase{
	
	@Test
	public void testFolderDeletion(){
		Folders oldFolders = app.getFolderHelper().getFolders();
		
		int index = generateRandomIndex(oldFolders.size());		
		String folderDeleted = app.getFolderHelper().deleteFolder(index);		
		
		Folders newFolders = app.getFolderHelper().getFolders();		
		assertThat(newFolders, equalTo(oldFolders.without(folderDeleted)));
	}

}
