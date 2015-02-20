package com.ssh.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.ssh.fw.ApplicationManager;
import com.ssh.fw.MZTargetSubsystems;
import com.ssh.fw.MZWorkflowConfigData;
import com.thoughtworks.xstream.XStream;

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
		String configFile = System.getProperty("configFile", "config\\application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		
		app = new ApplicationManager(properties);
	}	

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	}
	
	@DataProvider
	public static Iterator<Object[]> loadWfList() throws IOException {
		XStream xmlStream = new XStream();
		xmlStream.alias("subsystem", MZTargetSubsystems.class);
		xmlStream.alias("workflow", MZWorkflowConfigData.class);
		
		File configFile = new File(app.getProperty("wflist.cfg"));
		
		xmlStream.fromXML(configFile);
		//(List<MZWorkflowConfigData>)
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		List<MZTargetSubsystems> wfSubsystems = (List<MZTargetSubsystems>)xmlStream;
		
		for (MZTargetSubsystems configInfo : wfSubsystems) {
			list.add(new Object[]{configInfo});
		}
		
		return list.iterator();
	}
	
}
