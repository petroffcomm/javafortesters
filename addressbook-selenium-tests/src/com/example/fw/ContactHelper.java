package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.contacts.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends BaseHelper{

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private ContactData lastCreatedContact;
	private ContactData contactBeforeModification;
	private ContactData lastDeletedContact;
	private SortedListOf<ContactData> cachedContacts;
		
	public ContactData createContact(ContactData contact) {
		manager.navigateTo().mainPage();
		
		initContactCreation();
	    fillContactForm(contact);
	    lastCreatedContact = getContactFormData();
	    submitContactCreation();
	    gotoHomePage();
	    rebuildCache();
	    
	    return lastCreatedContact;
	}
	
	public ContactData modifyContact(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		
		initContactModification(index);
		
		//save contact data before modification
		contactBeforeModification = getContactFormData();
		
		fillContactForm(contact);		
		submitContactModification();
		gotoHomePage();
	    rebuildCache();
		
		return contactBeforeModification;
	}	
	
	public ContactData deleteContact(int index) {
		manager.navigateTo().mainPage();
		
		initContactModification(index);
		lastDeletedContact = getContactFormData();
		submitContactDeletion();
		gotoHomePage();
		rebuildCache();
		
		return lastDeletedContact;
	}
	
	public SortedListOf<ContactData> getContacts(){
		if (cachedContacts==null){
			rebuildCache();
		}
		
		return cachedContacts;
	}
	
	private void rebuildCache() {
		cachedContacts = new SortedListOf<ContactData>();
		
		manager.navigateTo().mainPage();
		List<WebElement> trows = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
		
		for (WebElement trow : trows) {						
			String fname = trow.findElement(By.xpath("./td[3]")).getText();
			String lname = trow.findElement(By.xpath("./td[2]")).getText();
			String fstEmail = trow.findElement(By.xpath("./td[4]")).getText();
			String homePhone = trow.findElement(By.xpath("./td[5]")).getText();
			
			cachedContacts.add(new ContactData().withFname(fname)
												.withLname(lname)
												.withFirstEmail(fstEmail)
												.withHomePhone(homePhone));			
		}		
	}

	public SortedListOf<String> getPrintedViewForContacts() {
		SortedListOf<ContactData> contacts = getContacts();
		
		SortedListOf<String> printedContacts = new SortedListOf<String>();
		for (ContactData contact: contacts){
			printedContacts.add(contact.toPrintedView());
		}
		
		return printedContacts;
	}
	
	public ContactHelper fillContactForm(ContactData contact) {
		type(By.name("firstname"), contact.getFname());
		type(By.name("lastname"), contact.getLname());
		type(By.name("address"), contact.getPrimaryAddr());
		type(By.name("home"), contact.getHomePhone());
		type(By.name("mobile"), contact.getMobilePhone());
		type(By.name("work"), contact.getWorkPhone());
		type(By.name("email"), contact.getFirstEmail());
		type(By.name("email2"), contact.getSecondEmail());
	    selectByText(By.name("bday"), contact.getBirthDay());
	    selectByText(By.name("bmonth"), contact.getBirthMonth());
		type(By.name("byear"), contact.getBirthYear());
		type(By.name("address2"), contact.getSecondAddr());
		type(By.name("phone2"), contact.getSecondHomePhone());
		
		return this;
	}
	
	/*
	This method returns field values from "contact edit form".
	Implemented for case when some of fields on form were not changed due to emptiness of some
	input values (see realization of method type(By locator, String text) in BaseHelper.java)
	in ContactData class instance but their actual values is needed for further comparison
	of actual and expected results.	
	In this case it's necessary to get actual information about values were actually contained by form 
	at the submission moment.
	*/
	public ContactData getContactFormData() {
		ContactData contact = new ContactData();
		
		contact.withFname(getFieldValue(By.name("firstname")));
		contact.withLname(getFieldValue(By.name("lastname")));
		contact.withFirstEmail(getFieldValue(By.name("email")));
		contact.withHomePhone(getFieldValue(By.name("home")));
		
		return contact;
	}
	
	//----------------------------------------------------------------------------------------

	public ContactHelper initContactCreation() {
		click(By.linkText("add new"));
		return this;
	}

	public ContactHelper initContactModification(int index) {
		selectContactByIndex(index);
		//table header row has the 1st index, so to access data row we need to increment index position by 1		
		click(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+ (index + 1) +"]/td[7]//img[@alt='Edit']"));
		
		return this;
	}

	public ContactHelper submitContactCreation() {
		click(By.name("submit"));
		cachedContacts = null;
		
		return this;
	}

	public ContactHelper submitContactModification() {
		click(By.xpath("//input[@type='submit'][@value='Update']"));
		cachedContacts = null;
		
		return this;
	}

	public ContactHelper submitContactDeletion() {
		click(By.xpath("//input[@type='submit'][@value='Delete']"));
		cachedContacts = null;
		
		return this;
	}
	
	public ContactHelper gotoHomePage() {
		click(By.linkText("home page"));
		return this;
	}
	
	private void selectContactByIndex(int index) {
		click(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+ (index + 1) +"]/td[1]/input[@type='checkbox']"));
	}
	
}