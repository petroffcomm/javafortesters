package com.example.fw;

import com.example.tests.Contact;

public class ContactHelper extends BaseHelper{

	public ContactHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void createContact(Contact contact) {
		initContactCreation();
		fillContactForm(contact);
		saveContact();
	}

	private void saveContact() {
		manager.getAutoItHelper().click("Save")
		.winWaitAndActivate("AddressBook Portable", "", 5000);
	}

	private void fillContactForm(Contact contact) {
		manager.getAutoItHelper().send(contact.getFirstName())
		.send(contact.getLastName());
	}

	private void initContactCreation() {
		manager.getAutoItHelper()
		.winWaitAndActivate("AddressBook Portable", "", 5000)
		.click("Add")
		.winWaitAndActivate("Add Contact", "", 5000);
	}

}
