package com.example.tests.contacts;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class ContactPrintTests extends TestBase {
	
	@Test
	public void testContactsWithPhonesPrinting(){
		
		//save old state
		SortedListOf<String> contactsInTable = app.getContactHelper().getPrintedViewForContacts();
		SortedListOf<String> contactsPrinted = app.getPrintedPhonesHelper().getContacts();
		
		assertThat(contactsInTable, equalTo(contactsPrinted));
	}

}
