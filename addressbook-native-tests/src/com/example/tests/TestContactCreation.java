package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestContactCreation extends TestBase {
	
	@BeforeClass(dependsOnGroups = "primary")
	public void cleanAppDataBase(){
		deleteFile(app.getProperty("app.data"));		
	}
	
	@Test(dataProvider = "generateRandomContact")
	public void createContactWithValidData(Contact contact){
		app.getContactHelper().createContact(contact);
		Contact createdContact = app.getContactHelper().getFirstContact();
		Assert.assertEquals(contact, createdContact);
	}

}
