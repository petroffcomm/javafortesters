package com.ssh.tests;

import org.testng.annotations.Test;

public class uxConnectionTest extends TestBase {
	
	@Test
	public void userShouldConnectToUXServer(){
		
		app.getFtpHelper().uploadFile("d:\\projects_data\\rgw10\\builds\\comp\\rgw-1.0.1.25-smpp_gw\\etc\\","/cdrs/work/in/", "org.ops4j.pax.logging.new.cfg");
		//app.getMzshHelper().runCommand("wflist");
		//app.getFtpHelper().downloadFile();
	}

}
