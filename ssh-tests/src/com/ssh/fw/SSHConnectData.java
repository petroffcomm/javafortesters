package com.ssh.fw;

public class SSHConnectData {

	public String host;
	public String login;
	public String password;
	
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

	public String getPassphrase() {
		return null;
	}

	public String getPassword() {
		return password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getHostAddr() {
		return host;
	}

}
