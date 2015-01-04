package com.example.tests.contacts;

import static com.example.tests.groups.GroupDataGenerator.generateRandomString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;

public class ContactDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length < 3){
			System.out.println("Please specify parameters: <amount of test data> <file> <format>");
			return;
		}
		
		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];
		
		if (file.exists()){
			System.out.println("File " + file + " exists - please remove it manually.");
			return;
		}
		
		List<ContactData> groups = generateRandomContacts(amount);
		
		if ("csv".equals(format)){
			saveContactsToCSVFile(groups, file);
		}else if ("xml".equals(format)){
			saveContactsToXMLFile(groups, file);
		}else{
			System.out.println("Unknown format: " + format);
			return;
		}

	}

	private static void saveContactsToXMLFile(List<ContactData> contacts, File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		String xml = xstream.toXML(contacts);
		
		FileWriter writer = new FileWriter(file);
		writer.write(xml);		
		writer.close();
	}
	
	public static List<ContactData> loadContactsFromXMLFile(File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		
		return (List<ContactData>)xstream.fromXML(file);
	}

	private static void saveContactsToCSVFile(List<ContactData> contacts, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(contact.getFname() + "," + contact.getLname() + "," + contact.getPrimaryAddr() + ","
						+ contact.getSecondAddr() + "," + contact.getHomePhone() + "," + contact.getSecondHomePhone() + ","
						+ contact.getMobilePhone() + "," + contact.getWorkPhone() + "," + contact.getFirstEmail() + ","
						+ contact.getSecondEmail() + "," + contact.getBirthDay() + "," + contact.getBirthMonth() + ","
						+ contact.getBirthYear() + 
						",!" + "\n");
		}
		writer.close();
	}

	public static List<ContactData> loadContactsFromCSVFile(File file) throws IOException{
		List<ContactData> list = new ArrayList<ContactData>();
		
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		
		while((line != null) && (line != "")){
			String[] part = line.split(",");
			ContactData contact = new ContactData()
				.withFname(part[0])
				.withLname(part[1])
				.withPrimaryAddr(part[2])
				.withSecondAddr(part[3])
				.withHomePhone(part[4])
				.withSecondHomePhone(part[5])
				.withMobilePhone(part[6])
				.withWorkPhone(part[7])
				.withFirstEmail(part[8])
				.withSecondEmail(part[9])
				.withBirthDay(part[10])
				.withBirthMonth(part[11])
				.withBirthYear(part[12]);
			
			list.add(contact);
			line = bufferedReader.readLine();
		}
		
		bufferedReader.close();
		reader.close();
		
		return list;
	}
	
	public static List<ContactData> generateRandomContacts(int amount) {
		List<ContactData> list = new ArrayList<ContactData>();
		for (int i = 0; i < amount; i++){			
			ContactData contact = new ContactData();
			
		    contact.withFname(generateRandomString("FName "))
		    		.withLname(generateRandomString("LName "))
		    		.withPrimaryAddr(generateRandomString("Address "))
		    		.withSecondAddr(generateRandomString("Secondary address "))
		    		.withHomePhone(generateRandomNumber())
		    		.withSecondHomePhone(generateRandomNumber())
		    		.withMobilePhone(generateRandomNumber())
		    		.withWorkPhone(generateRandomNumber())
		    		.withFirstEmail(generateRandomString("email")+"@some_host.com")
		    		.withSecondEmail(generateRandomString("email")+"2@some_host.com")
		    		.withBirthDay("1")
		    		.withBirthMonth("December")
		    		.withBirthYear("1980");
			
			list.add(contact);
		}
		return list;
	}
	
	public static String generateRandomNumber(){
		Random rnd = new Random();
		int val = rnd.nextInt();
		if (val < 0) val *= -1;
		return "" + val;
	}

}
