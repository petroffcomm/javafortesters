package com.example.fw;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.tests.contacts.ContactData;
import com.example.tests.groups.GroupData;
import com.example.utils.SortedListOf;

public class ApplicationManager {
	
	private Properties properties;
	
	private WebDriver driver;
	private String baseUrl;
	
	private HibernateHelper hibernateHelper;
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private PrintedPhonesHelper printedPhonesHelper;
	
	private ApplicationModel model;
	
	public ApplicationManager(Properties properties){
		this.properties = properties;
		
		this.model = new ApplicationModel();
		model.setGroups(getGroupsFromDB());
		model.setContacts(getContactsFromDB());
	}

	public void stop() {
	    driver.quit();
	}
	
	public NavigationHelper navigateTo(){
		if (navigationHelper==null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}
	
	public GroupHelper getGroupHelper(){
		if (groupHelper==null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}
	
	public ContactHelper getContactHelper(){
		if (contactHelper==null) {
			contactHelper = new ContactHelper(this);
		}
		return contactHelper;
	}
	
	public PrintedPhonesHelper getPrintedPhonesHelper(){
		if (printedPhonesHelper==null) {
			printedPhonesHelper = new PrintedPhonesHelper(this);
		}
		return printedPhonesHelper;
	}

	public HibernateHelper getHibernateHelper(){
		if (hibernateHelper==null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}
	
	public WebDriver getDriver() {
		
		if (driver==null) {
			String browser = properties.getProperty("browser");
			
			if ("firefox".equals(browser)){
				driver = new FirefoxDriver();
			}else if ("ie".equals(browser)) {
				driver = new InternetExplorerDriver();
			}else if ("chrome".equals(browser)) {
				System.setProperty("webdriver.chrome.driver", System.getenv("SELENIUM_HOME")+"\\chromedriver.exe");
				driver = new ChromeDriver();
			}else{
				throw new Error("Unsupported browser: " + browser);
			}
			
			baseUrl = properties.getProperty("baseUrl");
		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    driver.get(baseUrl);
		}
		
		return driver;
	}
	
	public ApplicationModel getModel(){
		return model;
	}

	public SortedListOf<ContactData> getContactsFromDB() {
		return getHibernateHelper().listContacts();
	}

	public SortedListOf<GroupData> getGroupsFromDB() {
		return getHibernateHelper().listGroups();
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}