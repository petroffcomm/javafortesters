package com.example.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.example.fw.ApplicationManager;

public class Sample {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileReader(new File("config\\application.properties")));
		ApplicationManager app = new ApplicationManager(props);
		//System.out.println(app.getHibernateHelper().listGroups());
		System.out.println(app.getHibernateHelper().getGroupByObjectId(169));
	}

}
