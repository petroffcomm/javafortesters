package com.example.fw;

import java.io.IOException;

public class ProcessHelper extends BaseHelper{

	private Process process;

	public ProcessHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}
	
	public void startAppUderTest() throws IOException{
		String command = manager.getProperty("app.path");
		process = Runtime.getRuntime().exec(command);
	}
	
	public void stopAppUderTest(){
		process.destroy();
	}

}
