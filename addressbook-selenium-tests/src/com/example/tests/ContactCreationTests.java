package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNonEmptyContactCreation() throws Exception {
    ContactData contact = new ContactData();
    contact.fname = "FName 1";
    contact.lname = "LName 1";
    contact.primary_addr = "Address 1";
    contact.second_addr = "Secondary address";
    contact.home_phone = "998941234567";
    contact.second_home_phone = "99893135689";
    contact.mobile_phone = "998937865454";
    contact.work_phone = "2349873629";
    contact.first_email = "email@test.com";
    contact.second_email = "elamil2@test.com";
    contact.birth_day = "1";
    contact.birth_month = "December"; 
    contact.birth_year = "1980";
	  
    openMainPage();
    gotoContactEditPage();   
    fillContactForm(contact);
    submitContactCreation();
    gotoHomePage();
  }
  
  @Test
  public void testEmptyContactCreation() throws Exception {
    ContactData contact = new ContactData();
	  
    openMainPage();
    gotoContactEditPage();   
    fillContactForm(contact);
    submitContactCreation();
    gotoHomePage();
  }
}