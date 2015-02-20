package com.samples;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.ssh.fw.MZTargetSubsystems;
import com.ssh.fw.MZWorkflowConfigData;
import com.ssh.tests.TestBase;
import com.thoughtworks.xstream.XStream;

public class ConfigSample extends TestBase{
	
	//@Test
	public void readWfList2() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("subsystem", MZTargetSubsystems.class);
		xstream.alias("workflow", MZWorkflowConfigData.class);
		
		File configFile = new File(app.getProperty("wflist.cfg"));
		xstream.fromXML(configFile);
	}
	
	@Test
	public void writeWfList() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("subsystem", MZTargetSubsystems.class);
		xstream.alias("workflow", MZWorkflowConfigData.class);
		
		MZTargetSubsystems subsystems = new MZTargetSubsystems();
		MZWorkflowConfigData wfData = new MZWorkflowConfigData()
										.withWFInputFolder("testinput")
										.withWFOutputFolder("testoutput")
										.withWFName("testWorkflow");
		
		subsystems.addWorkflow("test_subs", wfData);
		System.out.println(xstream.toXML(subsystems));
	}

}