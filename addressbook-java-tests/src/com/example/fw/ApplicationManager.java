package com.example.fw;

import java.util.Properties;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JFrameOperator;

public class ApplicationManager {
	
	private static ApplicationManager singleton;
	
	private Properties properties;

	private FolderHelper folderHelper;

	private JFrameOperator mainFrame;

	private MenuHelper menuHelper;
	
	public static ApplicationManager getInstance(){
		if (singleton == null){
			singleton = new ApplicationManager();
		}
		return singleton;
	}
	
	public void stop() {
		getApplication().requestClose();
	}
	
	public void setProperties(Properties properties){
		this.properties = properties;
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public String getProperty(String key, String defaultValue){
		return properties.getProperty(key, defaultValue);
	}

	public FolderHelper getFolderHelper() {
		if (folderHelper == null){
			folderHelper = new FolderHelper(this);
		}
		
		return folderHelper;
	}

	public JFrameOperator getApplication() {
		if (mainFrame == null){
			try {				
				System.setProperty("jAddressBook.home", "d:\\SelfTraining\\Trainings\\javafortesters\\install\\lesson6_full\\AddressBook\\data\\");
				System.setProperty("addressbook.DTD", "d:\\SelfTraining\\Trainings\\javafortesters\\install\\lesson6_full\\AddressBook\\data\\");
				
				new ClassReference("addressbook.AddressBookFrame").startApplication();
				mainFrame = new JFrameOperator("jAddressBook");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mainFrame;		
	}

	public MenuHelper getMenuHelper() {
		if (menuHelper == null){
			menuHelper = new MenuHelper(this);
		}
		
		return menuHelper;
	}

}