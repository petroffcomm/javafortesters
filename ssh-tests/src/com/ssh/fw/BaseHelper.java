package com.ssh.fw;

public class BaseHelper {
	
	protected ApplicationManager manager;
	
	public BaseHelper(ApplicationManager manager) {
		this.manager = manager;
	}
	
	protected void pause(int pause) {
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
