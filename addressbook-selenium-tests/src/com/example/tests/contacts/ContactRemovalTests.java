package com.example.tests.contacts;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class ContactRemovalTests extends TestBase{

	@Test
	public void deleteSomeContact(){
		
		//save old state from DB
		SortedListOf<ContactData> oldModelState = app.getModel().getContacts();
		
		int index = getRandomIndexForList(oldModelState);
		
		//actions
		ContactData contactDeleted = app.getContactHelper().deleteContact(index);
		
		//save new state from UI
		SortedListOf<ContactData> currentUIState = app.getContactHelper().getContacts();
		//save new state from DB
		SortedListOf<ContactData> currentDBState= app.getContactsFromDB();
		//save new state from Model
		SortedListOf<ContactData> currentModelState = app.getModel().getContacts();
		
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
