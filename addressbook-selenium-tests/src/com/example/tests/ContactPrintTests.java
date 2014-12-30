package com.example.tests;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactPrintTests extends TestBase {
	
	@Test
	public void testContactsWithPhonesPrinting(){
		
		//save old state
		SortedListOf<ContactData> contactsInTable = app.getContactHelper().getContacts();
		app.getPrintedPhonesHelper().getContacts();
		//SortedListOf<ContactData> printedContacts = app.get().getContacts();		
	}

}
