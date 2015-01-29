package com.example.tests.groups;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.example.tests.GroupBaseTest;
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
	SortedListOf<GroupData> oldList= app.getModel().getGroups();
    
    //actions
    app.getGroupHelper().createGroup(groupCreationData);
	    
    //save new state from UI
    SortedListOf<GroupData> newList= app.getGroupHelper().getGroups();
    
    //compare states
    assertThat(newList, equalTo(oldList.withAdded(groupCreationData)));
  }
  
}