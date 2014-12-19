package com.example.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{
	
	@Test
	public void modifySomeContact(){
		ContactData contact = new ContactData();
	    contact.fname = "firstName modified";
	    
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().initContactModification(1);	    
		app.getContactHelper().fillContactForm(contact);
	    app.getContactHelper().submitContactModification();
	    app.getContactHelper().gotoHomePage();
	}

}
