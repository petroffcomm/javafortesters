package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.groups.GroupData;
import com.example.utils.SortedListOf;

public class GroupHelper extends BaseHelper{

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private GroupData lastCreatedGroup;
	private GroupData groupBeforeModification;
	private GroupData lastDeletedGroup;
	private SortedListOf<GroupData> cachedGroups;
	
	public GroupData createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		
		initGroupCreation();
		fillGroupForm(group);
		lastCreatedGroup = getGroupFormData();
		submitGroupCreation();
		returnToGroupsPage();
		rebuildCache();
		
		return lastCreatedGroup;
	}
	
	public GroupData modifyGroup(int index, GroupData group) {
		manager.navigateTo().groupsPage();
		
	    initGroupModification(index);
	    
	    groupBeforeModification = getGroupFormData();
	    
	    fillGroupForm(group);
	    submitGroupModification();
	    returnToGroupsPage();
	    rebuildCache();
	    
		return groupBeforeModification;
	}
	
	public GroupData deleteGroup(int index) {
		manager.navigateTo().groupsPage();
		
		selectGroupByIndex(index);
		
		lastDeletedGroup = new GroupData().withName(getGroupNameByIndex(index));
		
		submitGroupDeletion();
		returnToGroupsPage();
		rebuildCache();
		
		return lastDeletedGroup;
	}
	
	public SortedListOf<GroupData> getGroups() {		
		if (cachedGroups == null){
			rebuildCache();
		}
		
		return cachedGroups;
	}
	
	private void rebuildCache() {
		cachedGroups = new SortedListOf<GroupData>();
		
		manager.navigateTo().groupsPage();
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		
		for (WebElement checkbox : checkboxes) {			
			String name = getGroupNameFromCheckboxTitle(checkbox);
			
			cachedGroups.add(new GroupData().withName(name));
		}
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
	
	//----------------------------------------------------------------------------------------

	public GroupHelper initGroupCreation() {
		click(By.name("new"));
		return this;
	}
	
	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
		
		return this;
	}

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		cachedGroups = null;
		
		return this;
	}
	
	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		cachedGroups = null;
		
		return this;
	}
	
	private GroupHelper submitGroupDeletion() {
		click(By.name("delete"));
		cachedGroups = null;
		
		return this;
	}
	
	public GroupHelper returnToGroupsPage() {
		click(By.linkText("group page"));
		return this;
	}

	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + (index+1) + "]"));
	}
	
	private String getGroupNameByIndex(int index) {
		WebElement checkbox = driver.findElement(By.xpath("//input[@name='selected[]'][" + (index+1) + "]"));				
		return getGroupNameFromCheckboxTitle(checkbox);
	}

	private String getGroupNameFromCheckboxTitle(WebElement checkbox) {
		//get checkbox title
		String title = checkbox.getAttribute("title");
		//extract checkbox title
		String name = title.substring("Select (".length(), title.length() - ")".length());
		
		return name;
	}

}