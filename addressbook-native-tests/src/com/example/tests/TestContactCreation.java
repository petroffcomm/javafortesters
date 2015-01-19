package com.example.tests;

import org.testng.annotations.Test;

public class TestContactCreation extends TestBase {
	
	@Test
	public void createContactWithValidData(){
		Contact contact = new Contact().setFristName("tester").setLastName("tester");
		app.getContactHelper().createContact(contact);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 

}
