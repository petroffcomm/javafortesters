package com.ssh.fw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class RemoteConnectionHelper extends BaseHelper {

	private Session sshSession;
	
	public RemoteConnectionHelper(ApplicationManager manager) {
		super(manager);
	}

	public RemoteConnectionHelper login(SSHConnectData connectData) {
		JSch jsch = new JSch();
		
		try {
			sshSession = jsch.getSession(connectData.getLogin(), connectData.getHostAddr(), 22);
			sshSession.setUserInfo(connectData);
			sshSession.connect();
		} catch (JSchException e) {
			e.printStackTrace();
		}
		
		return this;		
	}

	public RemoteConnectionHelper runCommand(String command) {
		if(sshSession.isConnected()){
			
			try {
				ChannelExec channel = (ChannelExec) sshSession.openChannel("exec");
				channel.setCommand(command);
				
				channel.setInputStream(null);
				channel.setErrStream(System.err);
				BufferedReader br = new BufferedReader(
										new InputStreamReader(channel.getInputStream()));
				
				channel.connect();
												
				String line = null;
				while ((line = br.readLine()) != null){
					System.out.println(line);
					
					try {
						Thread.sleep(50);
					} catch (Exception ee) {
					}
				}				
				
				channel.disconnect();
			} catch (JSchException | IOException e) {
				e.printStackTrace();
			}
			
		}else{
			throw new RuntimeException("You aren't logged in to any server");
		}
		return this;
	}

	public void closeRemoteConnection() {
		if(sshSession.isConnected()){
			sshSession.disconnect();
		}
	}

}
