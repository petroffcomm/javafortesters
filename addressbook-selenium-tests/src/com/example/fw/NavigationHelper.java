package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper{

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

}
