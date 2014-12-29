package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase{
	
	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contactModificationData){
		
	    //save old state
	    SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    //actions
		ContactData contactBeforeModification = app.getContactHelper().modifyContact(index, contactModificationData);
	    
	    //save new state
		SortedListOf<ContactData> newList = app.getContactHelper().getContacts();
	    
		//compare states
	    assertThat(newList, equalTo(oldList.without(contactBeforeModification).withAdded(contactModificationData)));
	}

}
