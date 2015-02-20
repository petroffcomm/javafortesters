package com.ssh.tests;

import org.testng.annotations.Test;

import com.ssh.fw.MZWorkflowConfigData;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConverterTest extends TestBase {
	
	@Test(dataProvider="loadWfList")
	public void directConversionShouldWork(MZWorkflowConfigData wfConfigInfo){
		System.out.println(wfConfigInfo);
		/*app.getFtpHelper().uploadFiles(wfConfigInfo.getWfInputFolder());
		app.getMzshHelper().convertFiles(wfConfigInfo.getWfName());
		app.getFtpHelper().downloadFiles(wfConfigInfo.getWfOutputFolder());*/
		
		//assertThat(app.getComparationHelper().expectedResult(), equalTo(app.getComparationHelper().actualResult()));
	}
	
}
