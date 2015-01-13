package com.example.tests;

import org.testng.annotations.Test;

import com.example.fw.Folders;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestFolderCreation extends TestBase {
	
	@Test(dataProvider = "folderNameGenerator", groups={"first"})
	public void testFolderCreation(String folder){
		
		Folders oldFolders = app.getFolderHelper().getFolders();
		assertThat(app.getFolderHelper().createFolder(folder), is(nullValue()));
		Folders newFolders = app.getFolderHelper().getFolders();
		
		assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));
		
	}
		
	@Test(dataProvider = "folderNameGenerator", groups={"second"}, dependsOnGroups = {"first"}, alwaysRun = true)
	public void testVariousFoldersCreation(String folder){	
		
		String folder2 = folder + "2";
		Folders oldFolders = app.getFolderHelper().getFolders();
		assertThat(app.getFolderHelper().createFolder(folder), is(nullValue()));
		Folders newFolders = app.getFolderHelper().getFolders();
		
		assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));
		
		assertThat(app.getFolderHelper().createFolder(folder2), is(nullValue()));
		Folders newFolders2 = app.getFolderHelper().getFolders();
		assertThat(newFolders2, equalTo(newFolders.withAdded(folder2)));
		
	}

	@Test(dataProvider = "folderNameGenerator", groups={"third"}, dependsOnGroups = {"second"}, alwaysRun = true)
	public void testFoldersWithSameNameCreation(String folder){
		
		Folders oldFolders = app.getFolderHelper().getFolders();
		assertThat(app.getFolderHelper().createFolder(folder), is(nullValue()));
		Folders newFolders = app.getFolderHelper().getFolders();
		
		assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));
		
		assertThat(app.getFolderHelper().createFolder(folder), containsString("Duplicated folder name"));
		Folders newFolders2 = app.getFolderHelper().getFolders();
		assertThat(newFolders2, equalTo(newFolders));
		
		app.getFolderHelper().deleteFolder(folder);
		
	}
	
}