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
		SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();
	    
		int index = getRandomIndexForList(oldList);
	
		//actions
		//get DB-record for modified group and pack it into ContactData-object
		ContactData dbFilledContactBeforeModification = app.getContactHelper().getContactFromDBByUIIndex(index);
		//modify contact
		ContactData uiFilledContactBeforeModification = app.getContactHelper().modifyContact(index, contactModificationData);
		//check, that UI form have the same field values as in corresponding DB-record
		assertThat(dbFilledContactBeforeModification, samePropertyValuesAs(uiFilledContactBeforeModification));

		//save new state from UI
		SortedListOf<ContactData> newUIList = app.getContactHelper().getContacts();
		//save new state from DB
		SortedListOf<ContactData> newDBList= (SortedListOf<ContactData>)app.getContactsFromDB();
		
		//compare states
		assertThat(newDBList, equalTo(oldList.without(uiFilledContactBeforeModification).withAdded(contactModificationData)));
		assertThat(newDBList, equalTo(newUIList));
	}

}
