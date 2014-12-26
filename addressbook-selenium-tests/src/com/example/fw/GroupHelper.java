package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;

public class GroupHelper extends BaseHelper{

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public GroupHelper initGroupCreating() {
		click(By.name("new"));
		return this;
	}

	public GroupHelper fillGroupForm(GroupData group) {
	    type(By.name("group_name"), group.getName());
	    type(By.name("group_header"), group.getHeader());
	    type(By.name("group_footer"), group.getFooter());
	    return this;
	}
	
	/*
	This method returns field values from "group edit form".
	Implemented for case when some of fields on form were not changed due to emptiness of some
	input values (see realization of method type(By locator, String text) in BaseHelper.java)
	in GroupData class instance but their actual values is needed for further comparison
	of actual and expected results.	
	In this case it's necessary to get actual information about values were actually contained by form 
	at the submission moment.
	*/
	public GroupData getGroupFormData() {
		GroupData group = new GroupData()
		.withName(getFieldValue(By.name("group_name")))
		.withHeader(getFieldText(By.name("group_header")))
		.withFooter(getFieldText(By.name("group_footer")));
		
		return group;
	}
	
	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		return this;
	}

	public GroupHelper returnToGroupsPage() {
		click(By.linkText("group page"));
		return this;
	}

	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + (index+1) + "]"));
	}
	
	public GroupHelper deleteGroup(int index) {
		selectGroupByIndex(index);
		click(By.name("delete"));
		return this;
	}

	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		return this;
	}

	public List<GroupData> getGroups() {
		List<GroupData> groups = new ArrayList<GroupData>();
		
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		
		for (WebElement checkbox : checkboxes) {
			
			String title = checkbox.getAttribute("title");			
			String name = title.substring("Select (".length(), title.length() - ")".length());
			
			groups.add(new GroupData().withName(name));
		}
		
		return groups;
	}
}