package com.example.tests.contacts;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
	    ContactData dbFilledContactBeforeModification = app.getContactHelper().getContactFromDBByUIIndex(index);
	    ContactData uiFilledContactBeforeModification = app.getContactHelper().modifyContact(index, contactModificationData);
	    assertTrue(dbFilledContactBeforeModification.isFullyEqualTo(uiFilledContactBeforeModification));
		
	    //save new state from UI
		SortedListOf<ContactData> newList = app.getContactHelper().getContacts();
	    
		//compare states
	    assertThat(newList, equalTo(oldList.without(uiFilledContactBeforeModification).withAdded(contactModificationData)));
	}

}
