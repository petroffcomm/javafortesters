package com.ssh.fw;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.yaml.snakeyaml.reader.StreamReader;

import static org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE;


public class FtpHelper extends BaseHelper{

	private FTPClient ftp;
	public static Logger log = Logger.getLogger(FtpHelper.class.getName());
	
	public FtpHelper(ApplicationManager manager) {
		super(manager);
	}

	private void initFtpConnection() {
		String ftpserver = manager.getProperty("ftp.host");
		String login = manager.getProperty("ftp.login");
		String password = manager.getProperty("ftp.password");
		String rootDir = manager.getProperty("ftp.rootDir");

		if (ftp == null) {
			ftp = new FTPClient();
		}
		if (ftp.isConnected()) {
			return;
		}

		try {
			ftp.connect(ftpserver);
		    ftp.login(login, password);
			ftp.changeWorkingDirectory(rootDir);
			ftp.mode(BINARY_FILE_TYPE);

	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void uploadFile(InputStream in, String targetFile) {
		initFtpConnection();
		try {
			ftp.storeFile(targetFile, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadFile(String sourceFile, String targetFile) {
		initFtpConnection();
		try {
			ftp.storeFile(targetFile, new FileInputStream(sourceFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadFile(String sourceDir, String targetDir, String fileName) {
		initFtpConnection();
		try {
			ftp.storeFile(targetDir + fileName, new FileInputStream(sourceDir + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void downloadFile(InputStream in, String targetFile) {
		initFtpConnection();
		try {
			ftp.storeFile(targetFile, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void downloadFiles(String sourceFolder) {
		initFtpConnection();
	}

	
	public void closeFtpConnection() {
		try {
			ftp.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void installConfigWithoutCaptcha(){
		String configFile = manager.getProperty("ftp.configFile");
		
		initFtpConnection();
		try {
			boolean backupExists = false;
			FTPFile[] files = ftp.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				if(files[i].getName().equals(configFile + ".bak")){
					backupExists = true;
					break;
				}
			}
			
			if (!backupExists) {
				ftp.rename(configFile, configFile + ".bak");
			}
			
			InputStream in = this.getClass().getResourceAsStream("/" + configFile);
			ftp.storeFile(configFile, in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		closeFtpConnection();		
	}
}
