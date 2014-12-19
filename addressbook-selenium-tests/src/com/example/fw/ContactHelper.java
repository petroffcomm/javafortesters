package com.example.fw;

import java.util.ArrayList;
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

	private void selectContactByIndex(int index) {
		click(By.xpath("//table[@id='maintable']/tbody/tr["+ (index + 1) +"]/td[1]/input[@type='checkbox']"));
	}
	
	public void initContactModification(int index) {
		selectContactByIndex(index);
		//table header row has the 1st index, so to access data row we need to increment index position by 1		
		click(By.xpath("//table[@id='maintable']/tbody/tr["+ (index + 1) +"]/td[7]//img[@alt='Edit']"));
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
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr[(position() > 1) and (position() < last())]"));
		
		for (WebElement row : rows) {
			ContactData contact = new ContactData();
			/*System.out.println(row.getTagName());
			System.out.println(row.getText());
			System.out.println(row.toString());*/
			String fname = row.findElement(By.xpath("//tr/td[3]")).getText();
			
			contact.fname = fname;
			contact.lname = row.findElement(By.xpath("//td[2]")).getText();
			contact.first_email = row.findElement(By.xpath("//td[4]")).getText();
			contact.mobile_phone = row.findElement(By.xpath("//td[5]")).getText();
			
			contacts.add(contact);			
		}
		
		return contacts;
	}

}