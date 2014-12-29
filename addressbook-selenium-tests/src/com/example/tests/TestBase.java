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
			String prefix = "test";
			GroupData group = new GroupData()
			.withName(generateRandomString(prefix))
			.withHeader(generateRandomString(prefix))
			.withFooter(generateRandomString(prefix));
						
			list.add(new Object[]{group});
		}
		return list.iterator();
	}
	
	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator(){
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 5; i++){			
			ContactData contact = new ContactData();
			
		    contact.withFname(generateRandomString("FName "));
		    contact.withLname(generateRandomString("LName "));
		    contact.withPrimaryAddr(generateRandomString("Address "));
		    contact.withSecondAddr(generateRandomString("Secondary address "));
		    contact.withHomePhone(generateRandomNumber());//"998941234567";
		    contact.withSecondHomePhone(generateRandomNumber());//"99893135689";
		    contact.withMobilePhone(generateRandomNumber());//"998937865454";
		    contact.withWorkPhone(generateRandomNumber());//"2349873629";
		    contact.withFirstEmail(generateRandomString("email")+"@some_host.com");
		    contact.withSecondEmail(generateRandomString("email")+"2@some_host.com");
		    contact.withBirthDay("1");
		    contact.withBirthMonth("December"); 
		    contact.withBirthYear("1980");
			
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
