package com.example.fw;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class BaseHelperWeb extends BaseHelper{
	
	protected WebDriver driver;
	public boolean acceptNextAlert = true;

	public BaseHelperWeb(ApplicationManager manager){
		super(manager);
		this.driver = manager.getDriver();
	}
	
	public boolean isElementPresent(By by) {
	    try {
	    	findElement(by);
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
	
	public WebElement findElement(By locator) {
		try {
			return driver.findElement(locator);
		} catch(Exception e) {
			return null;
		}		
	}
	
	protected void type(By locator, String text) {
		if (text != null) {
			findElement(locator).clear();
		    findElement(locator).sendKeys(text);			
		}		
	}

	protected void click(By locator) {
		findElement(locator).click();
	}
	
	protected void selectByText(By locator, String text) {
		if (text != null) {
			new Select(findElement(locator)).selectByVisibleText(text);
		}		
	}
	
	protected String getFieldValue(By locator) {
		return findElement(locator).getAttribute("value");
	}

	protected String getFieldText(By locator) {
		return findElement(locator).getText();
	}
	
	protected String getSelectedOptionValue(String name) {
		return findElement(By.xpath("//select[@name='" + name + "']/option[@selected='selected']")).getAttribute("value");
	}
	
	protected void openRelativeUrl(String pageLink){
		driver.get(manager.getProperty("baseUrl") + pageLink);
	}
	
	protected void openAbsoluteUrl(String absoluteLink){
		driver.get(absoluteLink);
	}

}
