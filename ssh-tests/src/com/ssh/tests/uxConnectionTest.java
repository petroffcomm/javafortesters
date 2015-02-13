package com.ssh.tests;

import org.testng.annotations.Test;

import com.ssh.fw.SSHConnectData;

public class uxConnectionTest extends TestBase {
	
	@Test
	public void userShouldConnectToUXServer(){
		SSHConnectData userConnectData = new SSHConnectData()
												.toHost("u02")
												.withLogin("mz")
												.withPassword("mzadmin");
		
		app.getRemoteConnectionHelper().login(userConnectData);
		app.getRemoteConnectionHelper().runCommand("mkdir conntestdirectory");
		app.getRemoteConnectionHelper().runCommand("ls -l");
		//app.getRemoteConnectionHelper().runCommand("mzsh ppetrov/dr wflist");
		app.getRemoteConnectionHelper().closeRemoteConnection();
	}

}
