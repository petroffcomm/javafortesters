package com.smpp.tests.submits;

import static com.smpp.tests.submits.SubmitSmDataGenerator.loadSubmitsFromXMLFile;
import static com.smpp.tests.submits.SubmitSmDataGenerator.wrapSubmitsForDataProviders;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.smpp.tests.TestBase;

public class TestSubmitMessages extends TestBase{
	
	@DataProvider
	public Iterator<Object[]> submitsFromFile() throws IOException{
		return wrapSubmitsForDataProviders(loadSubmitsFromXMLFile(new File("testdata\\submit_messages.xml"))).iterator();
	}

	@BeforeClass
	public void establishSMPPSession(){
		app.getSmppHelper().sendBindRequest();
	}
	
	@Test(dataProvider = "submitsFromFile")
	public void sendSubmit(SubmitSmData submitData){
		app.getSmppHelper().sendSubmitSm(submitData);
	}
	
	@AfterClass
	public void closeSMPPSession(){
		app.getSmppHelper().sendUnBindRequest();
	}

}
