package com.example.tests;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import static com.example.tests.GroupDataGenerator.loadGroupsFromCSVFile;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXMLFile;

public class GroupCreationTests extends TestBase{
  
  @DataProvider
  public Iterator<Object[]> groupsFromFile() throws IOException{
	return wrapGroupsForDataProviders(loadGroupsFromXMLFile(new File("groups.xml"))).iterator();
  }

@Test(dataProvider = "groupsFromFile")
  public void testGroupCreationWithValidData(GroupData groupCreationData) throws Exception {
    //save old state
	SortedListOf<GroupData> oldList= app.getGroupHelper().getGroups();
    
    //actions
    app.getGroupHelper().createGroup(groupCreationData);
	    
    //save new state
    SortedListOf<GroupData> newList= app.getGroupHelper().getGroups();
    
    //compare states
    assertThat(newList, equalTo(oldList.withAdded(groupCreationData)));
  }
  
}