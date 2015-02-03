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
		SortedListOf<ContactData> oldList = app.getModel().getContacts();
		
		int index = getRandomIndexForList(oldList);
		
		//actions
		ContactData contactDeleted = app.getContactHelper().deleteContact(index);
		
		//save new state from UI
		SortedListOf<ContactData> newUIList = app.getContactHelper().getContacts();
		//save new state from DB
		SortedListOf<ContactData> newDBList= app.getContactsFromDB();
		
		//compare states
		assertThat(newDBList, equalTo(oldList.without(contactDeleted)));
		assertThat(newDBList, equalTo(newUIList));
	}

}
