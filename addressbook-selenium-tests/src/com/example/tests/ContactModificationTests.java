package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{
	
	@Test
	public void modifySomeContact(){
		ContactData contact = new ContactData();
	    contact.fname = "firstName modified";
	    
		app.getNavigationHelper().openMainPage();
		
	    //save old state
	    List<ContactData> oldList = app.getContactHelper().getContacts();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
		app.getContactHelper().initContactModification(index);	    
		app.getContactHelper().fillContactForm(contact);
		ContactData contactFilled = app.getContactHelper().getContactFormData();
	    app.getContactHelper().submitContactModification();
	    app.getContactHelper().gotoHomePage();
	    
	    //save new state
	    List<ContactData> newList = app.getContactHelper().getContacts();
	    
	    oldList.remove(index);
	    oldList.add(contactFilled);
	    Collections.sort(oldList);
	    Collections.sort(newList);
	    assertEquals(newList, oldList);
	}

}
