package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;
import com.example.tests.contacts.ContactData;
import com.example.tests.groups.GroupData;

import static com.example.tests.contacts.ContactDataGenerator.generateRandomContacts;
import static com.example.tests.groups.GroupDataGenerator.generateRandomGroups;


public class TestBase {
	
	// Static modifier is used for this field because we need the possibility
	// to run 2 classes consequently in terms of one <test>-tag (see testNG docs).
	//
	// It's impossible to use non-static field because @BeforeTest "setUp"-method
	// which initializes "app" variable for newly created test class will not be executed
	// before running 2-nd class - only before the next <test>-tag in testNG-config.
	//
	// Reinitializing "app" variable for each test class in some @BeforeClass-method
	// makes no sense (for current realization).
	protected static ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile", "config\\application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }
	
	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator(){
		return wrapGroupsForDataProviders(generateRandomGroups(3)).iterator();
	}
	
	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator(){
		return wrapContactsForDataProviders(generateRandomContacts(3)).iterator();
	}
	
	public static List<Object[]> wrapGroupsForDataProviders(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		
		for (GroupData group: groups){
			list.add(new Object[]{group});
		}
		return list;
	}

	public static List<Object[]> wrapContactsForDataProviders(List<ContactData> contacts) {
		List<Object[]> list = new ArrayList<Object[]>();
		
		for (ContactData contact: contacts){
			list.add(new Object[]{contact});
		}
		return list;
	}
	
	public int getRandomIndexForList(List list){
		Random rnd = new Random();
		int size = list.size();
		
		if (size <= 0){
			return 0;
		}else if (size == 1){
			return size;
		}else{
			return rnd.nextInt(size - 1);
		}
	}
	
}
