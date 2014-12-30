package com.example.fw;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class PrintedPhonesHelper extends BaseHelper {

	public PrintedPhonesHelper(ApplicationManager manager) {
		super(manager);
	}
	
	public SortedListOf<ContactData> getContacts(){
		manager.navigateTo().printPhonesPage();
		getTableCells();
		return null;		
	}
	
	private void getTableCells(){
		List<WebElement> cells = driver.findElements(By.xpath("//table[@id='view']/tbody/tr/td[@*]"));
		
		Pattern ptrn = Pattern.compile("(^.*:$)|(:.*$)", Pattern.MULTILINE);
		for (WebElement cell : cells) {			
			Matcher mtcr = ptrn.matcher(cell.getText());
			System.out.println(mtcr.find() + " " + mtcr.groupCount());
			//System.out.println(cell.getText());
		}
	}

}
