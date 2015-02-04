package com.example.tests.contacts;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase{
	
	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contactModificationData){
		
		//save old state from DB
		SortedListOf<ContactData> oldModelState = app.getContactHelper().getContacts();
		
		int index = getRandomIndexForList(oldModelState);
	
		//actions
		//get DB-record for modified group and pack it into ContactData-object
		ContactData dbFilledContactBeforeModification = app.getContactHelper().getContactFromDBByUIIndex(index);
		//modify contact
		ContactData uiFilledContactBeforeModification = app.getContactHelper().modifyContact(index, contactModificationData);
		//check, that UI form have the same field values as in corresponding DB-record
		assertThat(dbFilledContactBeforeModification, samePropertyValuesAs(uiFilledContactBeforeModification));

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
