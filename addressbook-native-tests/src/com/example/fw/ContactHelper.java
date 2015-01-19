package com.example.fw;

import com.example.tests.Contact;

public class ContactHelper extends BaseHelper{

	private String appMainTitle;
	
	private String firstNameControl;
	private String lastNameControl;
	private String addButton;
	private String editButton;
	private String saveButton;
	private String cancelButton;
	private String deleteButton;

	public ContactHelper(ApplicationManager applicationManager) {
		super(applicationManager);
		loadControlNames();
	}

	private void loadControlNames() {
		this.appMainTitle = manager.getProperty("app.maintitle");
		
		this.firstNameControl = manager.getProperty("app.controls.inputTextFields.firstName");
		this.lastNameControl = manager.getProperty("app.controls.inputTextFields.lastName");
		
		this.addButton = manager.getProperty("app.controls.buttons.add");
		this.editButton = manager.getProperty("app.controls.buttons.edit");
		this.saveButton = manager.getProperty("app.controls.buttons.save");
		this.cancelButton = manager.getProperty("app.controls.buttons.cancel");
		this.deleteButton = manager.getProperty("app.controls.buttons.delete");
	}

	public void createContact(Contact contact) {
		initContactCreation();
		fillContactForm(contact);
		saveContact();
	}

	private void initContactCreation() {
		manager.getAutoItHelper()
		.winWaitAndActivate(appMainTitle, "", 5000)
		.click(addButton)
		.winWaitAndActivate("Add Contact", "", 5000);
	}
	
	private void fillContactForm(Contact contact) {
		manager.getAutoItHelper()
			.send(firstNameControl, contact.getFirstName())
			.send(lastNameControl, contact.getLastName());
	}
	
	private void saveContact() {
		manager.getAutoItHelper().click(saveButton)
		.winWaitAndActivate(appMainTitle, "", 5000);
	}

	public Contact getFirstContact() {		
		manager.getAutoItHelper()
			.focus("TListView1")
			.send("{DOWN}{SPACE}")
			.click(editButton).winWaitAndActivate("Update Contact", "", 5000);
		
		Contact contact = new Contact()
			.setFirstName(manager.getAutoItHelper().getText(firstNameControl))
			.setLastName(manager.getAutoItHelper().getText(lastNameControl));
		
		manager.getAutoItHelper().click(cancelButton).winWaitAndActivate(appMainTitle, "", 5000);
		
		return contact;
	}

}
