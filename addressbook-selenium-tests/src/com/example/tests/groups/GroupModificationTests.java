package com.example.tests.groups;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class GroupModificationTests extends TestBase{
	
	@Test(dataProvider = "randomValidGroupGenerator")
	public void modifySomeGroup(GroupData groupModificationData){	    
	    //save old state from DB
		SortedListOf<GroupData> oldList = app.getModel().getGroups();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    //actions
	    GroupData groupBeforeModificationDBFilled = app.getGroupHelper().getGroupFromDBByUIIndex(index);
	    GroupData groupBeforeModificationUIFilled = app.getGroupHelper().modifyGroup(index, groupModificationData);
	    assertThat(groupBeforeModificationDBFilled, equalTo(groupBeforeModificationUIFilled));	    
	    
	    //save new state from UI
	    SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
	    assertThat(newList, equalTo(oldList.without(groupBeforeModificationUIFilled).withAdded(groupModificationData)));
	}
	
}