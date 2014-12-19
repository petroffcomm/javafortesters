package com.example.tests;

public class ContactData implements Comparable<ContactData>{
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
	
	@Override
	public String toString() {
		return "ContactData [fname=" + fname + ", lname=" + lname
				+ ", mobile_phone=" + mobile_phone + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		/*result = prime * result
				+ ((first_email == null) ? 0 : first_email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result
				+ ((mobile_phone == null) ? 0 : mobile_phone.hashCode());*/
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactData other = (ContactData) obj;
		if (first_email == null) {
			if (other.first_email != null)
				return false;
		} else if (!first_email.equals(other.first_email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (mobile_phone == null) {
			if (other.mobile_phone != null)
				return false;
		} else if (!mobile_phone.equals(other.mobile_phone))
			return false;
		return true;
	}

	@Override
	public int compareTo(ContactData other) {
		return fname.compareToIgnoreCase(other.fname);
	}	
	
}