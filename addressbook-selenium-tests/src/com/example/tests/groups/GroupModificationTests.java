package com.example.tests.groups;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class GroupModificationTests extends TestBase{
	
	@Test(dataProvider = "randomValidGroupGenerator")
	public void modifySomeGroup(GroupData groupModificationData){	    
	    //save old state from DB
		SortedListOf<GroupData> oldList = app.getModel().getGroups();
	    
		int index = getRandomIndexForList(oldList);
	    
	    //actions
	    //get DB-record for modified group and pack it into GroupData-object
	    GroupData dbFilledGroupBeforeModification = app.getGroupHelper().getGroupFromDBByUIIndex(index);
	    
	    //modify group
	    GroupData uiFilledgroupBeforeModification = app.getGroupHelper().modifyGroup(index, groupModificationData);
	   
	    //check, that UI form have the same field values as in corresponding DB-record  
	    //assertThat(dbFilledGroupBeforeModification, samePropertyValuesAs(uiFilledgroupBeforeModification));	    
	    assertTrue(dbFilledGroupBeforeModification.isFullyEqualTo(uiFilledgroupBeforeModification));
	    
	    //save new state from UI
	    SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
	    assertThat(newList, equalTo(oldList.without(uiFilledgroupBeforeModification).withAdded(groupModificationData)));
	}
	
}