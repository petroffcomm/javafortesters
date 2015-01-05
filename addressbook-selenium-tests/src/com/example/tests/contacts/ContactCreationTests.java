package com.example.tests.contacts;

import static com.example.tests.contacts.ContactDataGenerator.loadContactsFromCSVFile;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> contactsFromFile() throws IOException{
    return wrapContactsForDataProviders(loadContactsFromCSVFile(new File("contacts.csv"))).iterator();
  }
	
  @Test(dataProvider = "randomValidContactGenerator")
  public void testContactCreationWithValidData(ContactData contactCreationData) throws Exception {
	    
    //save old state
    SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();

    //actions
    app.getContactHelper().createContact(contactCreationData);
    
    //save new state
    SortedListOf<ContactData> newList = app.getContactHelper().getContacts();
    
    //compare states
    assertThat(newList, equalTo(oldList.withAdded(contactCreationData)));
  }
  
}