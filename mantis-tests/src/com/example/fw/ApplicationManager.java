package com.example.fw;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
	
	private static ApplicationManager singleton;
	
	private Properties properties;	
	private WebDriver driver;
	
	private HibernateHelper hibernateHelper;
	private MailHelper mailHelper;
	private AccountHelper accountHelper;
	private JamesHelper jamesHelper;
	private FtpHelper ftpHelper;

	public ApplicationManager(Properties properties){
		this.properties = properties;
	}
	
	public static ApplicationManager getInstance(Properties properties) throws IOException{
		if (singleton == null){
			singleton = new ApplicationManager(properties);
		}
		return singleton;
	}

	public void stop() {
	    driver.quit();
	}
	
	public HibernateHelper getHibernateHelper(){
		if (hibernateHelper==null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}	

	public AccountHelper getAccountHelper() {
		if (accountHelper==null) {
			accountHelper = new AccountHelper(this);
		}
		return accountHelper;
	}
	
	public MailHelper getMailHelper() {
		if (mailHelper==null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}
	
	public JamesHelper getJamesHelper() {
		if (jamesHelper==null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;
	}
	
	public FtpHelper getFtpHelper() {
		if (ftpHelper==null) {
			ftpHelper = new FtpHelper(this);
		}
		return ftpHelper;
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
			
		    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		    driver.get(properties.getProperty("baseUrl"));
		}
		
		return driver;
	}
	
	
	public void setProperties(Properties properties){
		this.properties = properties;
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}

}