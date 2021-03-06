package com.example.tests.groups;

import static org.junit.Assert.assertThat;
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
		assertThat(dbFilledGroupBeforeModification, samePropertyValuesAs(uiFilledgroupBeforeModification));
		
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