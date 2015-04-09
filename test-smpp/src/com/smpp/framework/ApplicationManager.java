package com.smpp.framework;

import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
	
	private static ApplicationManager singleton;
	
	private Properties properties;
	
	private SmppHelper smppHelper;

	
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
		if (smppHelper != null) {
			smppHelper.close();
		}
	}
	
	public SmppHelper getSmppHelper(){
		if (smppHelper == null) {
			smppHelper = new SmppHelper(this);
		}
		return smppHelper;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}