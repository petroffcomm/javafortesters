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

	public void initGroupCreating() {
		click(By.name("new"));
	}

	public void fillGroupForm(GroupData group) {
	    type(By.name("group_name"), group.name);
	    type(By.name("group_header"), group.header);
	    type(By.name("group_footer"), group.footer);
	}
	
	/*
	This method returns field values from "group edit form".
	Implemented for case when some of fields were not filled due to emptiness of some input values
	(see realization of method type(By locator, String text) in BaseHelper.java)
	and it's necessary to get actual information about values were actually contained by form 
	at the submission moment.
	This information is needed for further comparison of actual and expected results 
	*/
	public GroupData getGroupFormData() {
		GroupData group = new GroupData();
		
		group.name = getFieldValue(By.name("group_name"));
		group.header = getFieldText(By.name("group_header"));
		group.footer = getFieldText(By.name("group_footer"));
		
		return group;
	}
	
	public void submitGroupCreation() {
		click(By.name("submit"));
	}

	public void returnToGroupsPage() {
		click(By.linkText("group page"));
	}

	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + (index+1) + "]"));
	}
	
	public void deleteGroup(int index) {
		selectGroupByIndex(index);
		click(By.name("delete"));
	}

	public void initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));		
	}

	public void submitGroupModification() {
		click(By.name("update"));
	}

	public List<GroupData> getGroups() {
		List<GroupData> groups = new ArrayList<GroupData>();
		
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		
		for (WebElement checkbox : checkboxes) {
			GroupData group = new GroupData();
			String title = checkbox.getAttribute("title");			
			group.name = title.substring("Select (".length(), title.length() - ")".length());
			groups.add(group);
		}
		
		return groups;
	}
}