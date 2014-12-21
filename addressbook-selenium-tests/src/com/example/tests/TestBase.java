package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }
	
	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator(){
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 5; i++){
			GroupData group = new GroupData();
			String prefix = "test";
			
			group.name = generateRandomString(prefix);
			group.header = generateRandomString(prefix);
			group.footer = generateRandomString(prefix);
			
			list.add(new Object[]{group});
		}
		return list.iterator();
	}
	
	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator(){
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 5; i++){			
			ContactData contact = new ContactData();
			
		    contact.fname = generateRandomString("FName ");
		    contact.lname = generateRandomString("LName ");
		    contact.primary_addr = generateRandomString("Address ");
		    contact.second_addr = generateRandomString("Secondary address ");
		    contact.home_phone = generateRandomNumber();//"998941234567";
		    contact.second_home_phone = generateRandomNumber();//"99893135689";
		    contact.mobile_phone = generateRandomNumber();//"998937865454";
		    contact.work_phone = generateRandomNumber();//"2349873629";
		    contact.first_email = generateRandomString("email")+"@some_host.com";
		    contact.second_email = generateRandomString("email")+"2@some_host.com";
		    contact.birth_day = "1";
		    contact.birth_month = "December"; 
		    contact.birth_year = "1980";
			
			list.add(new Object[]{contact});
		}
		return list.iterator();
	}
	
	public String generateRandomString(String prefix){
		Random rnd = new Random();
		if (rnd.nextInt(3) == 0){
			return "";
		} else {
			return prefix + rnd.nextInt();
		}
	}
	
	public String generateRandomNumber(){
		Random rnd = new Random();
		int val = rnd.nextInt();
		if (val < 0) val *= -1;
		return "" + val;
	}
}
