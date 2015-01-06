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
	    //save old state
		SortedListOf<GroupData> oldList= app.getGroupHelper().getGroups();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    //actions
	    GroupData groupBeforeModification = app.getGroupHelper().modifyGroup(index, groupModificationData);
	    
	    //save new state
	    SortedListOf<GroupData> newList= app.getGroupHelper().getGroups();
	    
	    //compare states
	    assertThat(newList, equalTo(oldList.without(groupBeforeModification).withAdded(groupModificationData)));
	}
	
}