package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelperWeb{

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void mainPage() {
		if (! isOnMainPage()){			
			click(By.linkText("home"));
		}else{
			return;
		}		
	}

	private boolean isOnMainPage() {
		return driver.findElements(By.id("maintable")).size() > 0; 
	}

	public void groupsPage() {
		if (! isOnGroupsPage()){
			click(By.linkText("groups"));
		}else{
			return;
		}		
	}

	private boolean isOnGroupsPage() {
		if (driver.getCurrentUrl().contains("/group.php")
			&& driver.findElements(By.name("new")).size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void printPhonesPage() {
		if (! isOnPrintPhonesPage()){
			click(By.linkText("print phones"));
		}else{
			return;
		}		
	}

	private boolean isOnPrintPhonesPage() {
		if (driver.getCurrentUrl().contains("/view.php?all&print&phones")){
			return true;
		}else{
			return false;
		}
	}

}
