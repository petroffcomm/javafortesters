package com.example.tests.groups;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase{
	
	@Test
	public void deleteSomeGroup(){	    
	    //save old state from DB
		SortedListOf<GroupData> oldList = app.getModel().getGroups();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);

	    //actions
		GroupData groupDeleted = app.getGroupHelper().deleteGroup(index);
	    
	    //save new state from UI
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states	    
	    assertThat(newList, equalTo(oldList.without(groupDeleted)));
	}

}
