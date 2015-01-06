package com.example.fw;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.utils.SortedListOf;

public class PrintedPhonesHelper extends BaseHelper {

	public PrintedPhonesHelper(ApplicationManager manager) {
		super(manager);
	}
	
	public SortedListOf<String> getContacts(){
		manager.navigateTo().printPhonesPage();
		return getDataFromTableCells();
	}
	
	private SortedListOf<String> getDataFromTableCells(){
		List<WebElement> cells = driver.findElements(By.xpath("//table[@id='view']/tbody/tr/td[@*]"));
		
		SortedListOf<String> printedContactPhones = new SortedListOf<String>();
		
		Pattern pattern = Pattern.compile("^(.*):\\s*$|^[HMWP]:\\s(\\d+)$", Pattern.MULTILINE);
		
		for (WebElement cell : cells) {
			Matcher matcher = pattern.matcher(cell.getText());
			
			int idx = 1;
			StringBuffer contactRecord = new StringBuffer();
			while(matcher.find() && (idx < 3)){
				contactRecord.append(" ").append(matcher.group(idx).trim());
				idx++;
			}
			
			printedContactPhones.add(contactRecord.toString().trim());
		}
		
		return printedContactPhones;
	}

}
