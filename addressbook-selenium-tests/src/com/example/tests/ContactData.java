package com.example.tests;

public class ContactData implements Comparable<ContactData>{
	private String fname;
	private String lname;
	private String primaryAddr;
	private String secondAddr;
	private String homePhone;
	private String mobilePhone;
	private String workPhone;
	private String firstEmail;
	private String secondEmail;
	private String secondHomePhone;
	private String birthDay;
	private String birthMonth;
	private String birthYear;	

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getPrimaryAddr() {
		return primaryAddr;
	}

	public String getSecondAddr() {
		return secondAddr;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getFirstEmail() {
		return firstEmail;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public String getSecondHomePhone() {
		return secondHomePhone;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}
	
	public ContactData withFname(String fname) {
		this.fname = fname;
		return this;
	}

	public ContactData withLname(String lname) {
		this.lname = lname;
		return this;
	}

	public ContactData withPrimaryAddr(String primaryAddr) {
		this.primaryAddr = primaryAddr;
		return this;
	}

	public ContactData withSecondAddr(String secondAddr) {
		this.secondAddr = secondAddr;
		return this;
	}

	public ContactData withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public ContactData withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withFirstEmail(String firstEmail) {
		this.firstEmail = firstEmail;
		return this;
	}

	public ContactData withSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
		return this;
	}

	public ContactData withSecondHomePhone(String secondHomePhone) {
		this.secondHomePhone = secondHomePhone;
		return this;
	}

	public ContactData withBirthDay(String birthDay) {
		this.birthDay = birthDay;
		return this;
	}

	public ContactData withBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
		return this;
	}

	public ContactData withBirthYear(String birthYear) {
		this.birthYear = birthYear;
		return this;
	}
	
	public ContactData(){
		this.birthDay = "-";
		this.birthMonth = "-";
	}
	
	public ContactData(String fname, String lname,
			String primary_addr, String second_addr, String home_phone,
			String mobile_phone, String work_phone, String first_email,
			String second_email, String second_home_phone, String birth_day,
			String birth_month, String birth_year) {
		this.fname = fname;
		this.lname = lname;
		this.primaryAddr = primary_addr;
		this.secondAddr = second_addr;
		this.homePhone = home_phone;
		this.mobilePhone = mobile_phone;
		this.workPhone = work_phone;
		this.firstEmail = first_email;
		this.secondEmail = second_email;
		this.secondHomePhone = second_home_phone;
		this.birthDay = birth_day;
		this.birthMonth = birth_month;
		this.birthYear = birth_year;
	}	

	@Override
	public String toString() {
		return "ContactData [fname=" + fname + ", lname=" + lname
				+ ", homePhone=" + homePhone + ", firstEmail=" + firstEmail
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstEmail == null) ? 0 : firstEmail.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result
				+ ((homePhone == null) ? 0 : homePhone.hashCode());
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
		if (firstEmail == null) {
			if ((other.firstEmail != null) && (!other.firstEmail.equals("")))
				return false;
		} else if (!firstEmail.equals(other.firstEmail))
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
		if (homePhone == null) {
			if ((other.homePhone != null) && (!other.homePhone.equals("")))
				return false;
		} else if (!homePhone.equals(other.homePhone))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ContactData other) {
		int result = comparisonWithCheckForNULL(fname, other.fname);
		if (result == 0){
			result = comparisonWithCheckForNULL(lname, other.lname);
			if(result == 0){
				result = comparisonWithCheckForNULL(firstEmail, other.firstEmail);
				if(result == 0){
					result = comparisonWithCheckForNULL(homePhone, other.homePhone);
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