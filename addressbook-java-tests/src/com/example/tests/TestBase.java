package com.example.tests;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected Logger log = Logger.getLogger("TEST");
	protected ApplicationManager app;

	@BeforeClass
	//@Parameters({"configFile"})
	public void setUp(/*@Optional String configFile*/) throws Exception {
		/*if (configFile == null){
			configFile = System.getProperty("configFile");
		}
		if (configFile == null){
			configFile = System.getenv("configFile");
		}
		if (configFile == null){
			configFile = "application.properties";
		}
		
		Properties properties = new Properties();
		properties.load(new FileReader(configFile));*/
		log.info("setUp start");
		app = ApplicationManager.getInstance();
		//app.setProperties(properties);
		log.info("setUp end");
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		log.info("tearDown start");
		app.stop();
		log.info("tearDown end");
	}
	
	@DataProvider
	protected Iterator<Object[]> folderNameGenerator(){
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[]{generateRandomString("folder")});
		
		return list.iterator();	
	}
	
	protected static String generateRandomString(String prefix){
		Random rnd = new Random();
		return prefix + rnd.nextInt();
	}
	
	protected int generateRandomIndex(int size) {
		Random rnd = new Random();
		
		if (size <= 0){
			return 0;
		}else if (size == 1){
			return size;
		}else{
			return rnd.nextInt(size - 1);
		}	    
	}
	
}
