package com.example.tests.groups;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

import static com.example.tests.groups.GroupDataGenerator.loadGroupsFromCSVFile;
import static com.example.tests.groups.GroupDataGenerator.loadGroupsFromXMLFile;

public class GroupCreationTests extends TestBase{
  
  @DataProvider
  public Iterator<Object[]> groupsFromFile() throws IOException{
	return wrapGroupsForDataProviders(loadGroupsFromXMLFile(new File("groups.xml"))).iterator();
  }

  @Test(dataProvider = "groupsFromFile")
  public void testGroupCreationWithValidData(GroupData groupCreationData) throws Exception {
	//save old state from DB
	SortedListOf<GroupData> oldModelState = app.getModel().getGroups();
    
    //actions
    app.getGroupHelper().createGroup(groupCreationData);
	
    
    //save new state from UI
    SortedListOf<GroupData> currentUIState = app.getGroupHelper().getGroups();
    //save new state from DB
    SortedListOf<GroupData> currentDBState = app.getGroupsFromDB();
    //save new state from Model
    SortedListOf<GroupData> currentModelState = app.getModel().getGroups();
    
    
    //compare states
    if("yes".equals(app.getProperty("check.db"))){
    	assertThat(currentModelState, equalTo(currentDBState));
    }
    if("yes".equals(app.getProperty("check.ui"))){
    	assertThat(currentModelState, equalTo(currentUIState));
    }
    
    if("yes".equals(app.getProperty("check.db_to_ui"))){
    	assertThat(currentDBState, equalTo(currentUIState));
    }
  }
  
}