package com.smpp.tests;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.smpp.framework.ApplicationManager;

public class TestBase {
	
	// Static modifier is used for this field because we need the possibility
	// to run 2 classes consequently in terms of one <test>-tag (see testNG docs).
	//
	// It's impossible to use non-static field because @BeforeTest "setUp"-method
	// which initializes "app" variable for newly created test class will not be executed
	// before running 2-nd class - only before the next <test>-tag in testNG-config.
	//
	// Reinitializing "app" variable for each test class in some @BeforeClass-method
	// makes no sense (for current realization).
	protected static ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile", "conf\\application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	}
	
}
