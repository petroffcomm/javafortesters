package com.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.fw.Folders;

public class TestFolderDeletion extends TestBase{
	
	@Test
	public void testFolderDeletion(){
		Folders oldFolders = app.getFolderHelper().getFolders();
		
		//Random rnd = new Random();
	    //int index = rnd.nextInt(oldFolders.size())-1;
		int index = 1;
		
		String folderDeleted = app.getFolderHelper().deleteFolder(index);
		
		Folders newFolders = app.getFolderHelper().getFolders();
		
		assertThat(newFolders, equalTo(oldFolders.without(folderDeleted)));
	}

}
