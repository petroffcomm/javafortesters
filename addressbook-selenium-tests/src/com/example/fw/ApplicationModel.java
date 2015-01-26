package com.example.fw;

import java.util.List;

import com.example.tests.contacts.ContactData;
import com.example.tests.groups.GroupData;
import com.example.utils.SortedListOf;

public class ApplicationModel {
	private SortedListOf<GroupData> groups;
	private SortedListOf<ContactData> contacts;
	
	public SortedListOf<GroupData> getGroups(){		
		return groups;
	}
	
	public SortedListOf<ContactData> getContacts(){		
		return contacts;
	}
	
	public void setGroups(List<GroupData> groups){
		this.groups = new SortedListOf<GroupData>(groups);
	}
	
	public void setContacts(List<ContactData> contacts){
		this.contacts = new SortedListOf<ContactData>(contacts);
	}

	public ApplicationModel addGroup(GroupData group) {
		groups.add(group);
		return this;
	}
	
	public ApplicationModel addGroup(ContactData contact) {
		contacts.add(contact);
		return this;
	}

	public ApplicationModel removeGroup(GroupData group) {
		groups.remove(group);
		return this;
	}
	
	public ApplicationModel removeGroup(ContactData contact) {
		contacts.remove(contact);
		return this;
	}
}
