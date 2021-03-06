package com.ssh.fw;

import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
	
	private static ApplicationManager singleton;
	
	private Properties properties;	

	private FtpHelper ftpHelper;
	private MzshHelper mzshHelper;
	private SSHConnectionHelper remoteConnectionHelper;

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
		if (ftpHelper != null) {
			ftpHelper.closeFtpConnection();
		}
		
		if (mzshHelper != null) {
			mzshHelper.closeConnection();
		}
		
		if (remoteConnectionHelper != null) {
			remoteConnectionHelper.closeRemoteConnection();
		}		
	}	

	public FtpHelper getFtpHelper() {
		if (ftpHelper == null) {
			ftpHelper = new FtpHelper(this);
		}
		return ftpHelper;
	}
	
	public MzshHelper getMzshHelper() {
		if (mzshHelper == null) {
			mzshHelper = new MzshHelper(this);
		}
		return mzshHelper;
	}
	
	public SSHConnectionHelper getSSHConnectionHelper() {
		if (remoteConnectionHelper == null) {
			remoteConnectionHelper = new SSHConnectionHelper(this);
		}
		return remoteConnectionHelper;
	}
	
	public void setProperties(Properties properties){
		this.properties = properties;
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}

}