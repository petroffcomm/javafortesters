package com.example.fw;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public abstract class BaseHelperWeb extends BaseHelper{
	
	protected static final int CREATION = 1;
	protected static final int MODIFICATION = 2;
	protected static final int DELETION = 3;
	
	protected WebDriver driver;
	public boolean acceptNextAlert = true;

	public BaseHelperWeb(ApplicationManager manager){
		super(manager);
		this.driver = manager.getDriver();
	}
	
	public boolean isElementPresent(By by) {
	    try {
	    	driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	public boolean isAlertPresent() {
	    try {
	    	driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	public String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	    	acceptNextAlert = true;
	    }
	  }

	protected void type(By locator, String text) {
		if (text != null) {
			driver.findElement(locator).clear();
		    driver.findElement(locator).sendKeys(text);			
		}		
	}

	protected void click(By locator) {
		driver.findElement(locator).click();
	}
	
	protected void selectByText(By locator, String text) {
		if (text != null) {
			new Select(driver.findElement(locator)).selectByVisibleText(text);
		}		
	}	

	protected String getFieldValue(By locator) {
		return driver.findElement(locator).getAttribute("value");
	}

	protected String getFieldText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	protected String getSelectedOptionValue(String name) {
		return driver.findElement(By.xpath("//select[@name='" + name + "']/option[@selected='selected']")).getAttribute("value");
	}

}
