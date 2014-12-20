package com.example.fw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;

public class ContactHelper extends BaseHelper{

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void submitContactCreation() {
		click(By.name("submit"));
	}

	public void gotoHomePage() {
		click(By.linkText("home page"));
	}
	
	public void fillContactForm(ContactData contact) {
		type(By.name("firstname"), contact.fname);
		type(By.name("lastname"), contact.lname);
		type(By.name("address"), contact.primary_addr);
		type(By.name("home"), contact.home_phone);
		type(By.name("mobile"), contact.mobile_phone);
		type(By.name("work"), contact.work_phone);
		type(By.name("email"), contact.first_email);
		type(By.name("email2"), contact.second_email);
	    selectByText(By.name("bday"), contact.birth_day);
	    selectByText(By.name("bmonth"), contact.birth_month);
		type(By.name("byear"), contact.birth_year);
		type(By.name("address2"), contact.second_addr);
		type(By.name("phone2"), contact.second_home_phone);
	}
	
	/*
	This method returns field values from "contact edit form".
	Implemented for case when some of fields were not filled due to emptiness of some input values
	(see realization of method type(By locator, String text) in BaseHelper.java)
	and it's necessary to get actual information about values were actually contained by form 
	at the submission moment.
	This information is needed for further comparison of actual and expected results
	*/
	public ContactData getContactFormData() {
		ContactData contact = new ContactData();
		
		contact.fname = getFieldValue(By.name("firstname"));
		contact.lname = getFieldValue(By.name("lastname"));
		contact.first_email = getFieldValue(By.name("email"));
		contact.home_phone = getFieldValue(By.name("home"));
		
		return contact;
	}
	
	private void selectContactByIndex(int index) {
		click(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+ (index + 1) +"]/td[1]/input[@type='checkbox']"));
	}
	
	public void initContactModification(int index) {
		selectContactByIndex(index);
		//table header row has the 1st index, so to access data row we need to increment index position by 1		
		click(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']["+ (index + 1) +"]/td[7]//img[@alt='Edit']"));
	}

	public void submitContactModification() {
		click(By.xpath("//input[@type='submit'][@value='Update']"));
	}

	public void submitContactDeletion() {
		click(By.xpath("//input[@type='submit'][@value='Delete']"));
	}
	
	public void deleteContact(int index) {
		initContactModification(index);
		submitContactDeletion();
	}

	public List<ContactData> getContacts() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		
		List<WebElement> trows = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
		for (WebElement trow : trows) {
			ContactData contact = new ContactData();
						
			contact.fname = trow.findElement(By.xpath("./td[3]")).getText();
			contact.lname = trow.findElement(By.xpath("./td[2]")).getText();
			contact.first_email = trow.findElement(By.xpath("./td[4]")).getText();
			contact.home_phone = trow.findElement(By.xpath("./td[5]")).getText();
			
			contacts.add(contact);			
		}				
		return contacts;
	}
}