package com.example.tests.groups;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase{
	
	@Test
	public void deleteSomeGroup(){
		//save old state from DB
		SortedListOf<GroupData> oldList = app.getModel().getGroups();
		
		int index = getRandomIndexForList(oldList);

		//actions
		GroupData groupDeleted = app.getGroupHelper().deleteGroup(index);
		
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
