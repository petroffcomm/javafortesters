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
				+ ", work_phone=" + work_phone + ", first_email=" + first_email
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((first_email == null) ? 0 : first_email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result
				+ ((home_phone == null) ? 0 : home_phone.hashCode());
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
			if ((other.first_email != null) && (!other.first_email.equals("")))
				return false;
		} else if (!first_email.equals(other.first_email))
			return false;
		if (fname == null) {
			if ((other.fname != null) && (!other.fname.equals("")))
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if ((other.lname != null) && (!other.lname.equals("")))
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (home_phone == null) {
			if ((other.home_phone != null) && (!other.home_phone.equals("")))
				return false;
		} else if (!home_phone.equals(other.home_phone))
			return false;
		return true;
	}

	@Override
	public int compareTo(ContactData other) {
		int result = comparisonWithCheckForNULL(fname, other.fname);
		if (result == 0){
			result = comparisonWithCheckForNULL(lname, other.lname);
			if(result == 0){
				result = comparisonWithCheckForNULL(first_email, other.first_email);
				if(result == 0){
					result = comparisonWithCheckForNULL(home_phone, other.home_phone);
					if(result == 0){
						return result;
					}
				}
			}			
		}
		return result;
	}
	
	private int comparisonWithCheckForNULL(Object obj1, Object obj2){
		String param1 = (String) obj1;
		String param2 = (String) obj2;
		
		int result = 0;
		if ((obj1 == null) && (obj2 == null)){
			result = 0;
		} else if((obj1 == null) && (obj2 != null) && (param2 != "")){
			result = -1;
		} else if((param1 != "") && (obj1 != null) && (obj2 == null)){
			result = 1;
		} else if((obj1 != null) && (obj2 != null)){
			result = param1.compareToIgnoreCase(param2);
		}
		return result;
	}
}