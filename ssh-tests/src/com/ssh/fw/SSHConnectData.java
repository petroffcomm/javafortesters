package com.ssh.fw;

import com.jcraft.jsch.*;

public class SSHConnectData implements UserInfo {

	private String host;
	private String login;
	private String password;
	
	public SSHConnectData toHost(String host) {
		this.host = host;
		return this;
	}

	public SSHConnectData withLogin(String login) {
		this.login = login;
		return this;
	}

	public SSHConnectData withPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String getPassphrase() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getHostAddr() {
		return host;
	}

	@Override
	public boolean promptPassword(String message) {
		return true; //password is always filled
	}

	@Override
	public boolean promptPassphrase(String message) {
		return true;
	}

	@Override
	public boolean promptYesNo(String message) {
		return true;
	}

	@Override
	public void showMessage(String message) {		
	}

}
