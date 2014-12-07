package com.example.tests;

public class ContactData {
	public String fname;
	public String lname;
	public String primary_addr;
	public String second_addr;
	public String home_phone;
	public String mobile_phone;
	public String work_phone;
	public String first_email;
	public String second_email;
	public String second_home_phone;
	public String birth_day;
	public String birth_month;
	public String birth_year;
	
	public ContactData(){
		//empty selections for drop down lists
		this.birth_day = "-";
		this.birth_month = "-";
	}
	public ContactData(String fname, String lname,
			String primary_addr, String second_addr, String home_phone,
			String mobile_phone, String work_phone, String first_email,
			String second_email, String second_home_phone, String birth_day,
			String birth_month, String birth_year) {
		this.fname = fname;
		this.lname = lname;
		this.primary_addr = primary_addr;
		this.second_addr = second_addr;
		this.home_phone = home_phone;
		this.mobile_phone = mobile_phone;
		this.work_phone = work_phone;
		this.first_email = first_email;
		this.second_email = second_email;
		this.second_home_phone = second_home_phone;
		this.birth_day = birth_day;
		this.birth_month = birth_month;
		this.birth_year = birth_year;
	}
}