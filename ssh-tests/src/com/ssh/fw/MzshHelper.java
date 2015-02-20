package com.ssh.fw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class MzshHelper extends BaseHelper {

	private String sshHost;
	private String sshLogin;
	private String sshPwd;
	private String mzshLogin;
	private String mzshPwd;
	
	private Connection sshConnection;
	private Session sshSession;

	public MzshHelper(ApplicationManager manager) {
		super(manager);		
		loadConfig();
		sshLogin();
		openSshSession();
	}

	private void loadConfig() {
		sshHost = manager.getProperty("ssh.host");
		sshLogin = manager.getProperty("ssh.login");
		sshPwd = manager.getProperty("ssh.password");
		mzshLogin = manager.getProperty("mzsh.login");
		mzshPwd = manager.getProperty("mzsh.password");
	}
	
	private void sshLogin() {
		sshConnection = new Connection(sshHost);
		
		try {			
			sshConnection.connect();
			
			boolean isConnected = sshConnection.authenticateWithPassword(sshLogin, sshPwd);
            System.out.println("------\nConnection result: " + isConnected + "\n------");
            
		} catch (IOException e) {
			throw new RuntimeException("An exception occurred while trying to connect to the host: " + sshHost + ", Exception = " + e.getMessage(), e);
		}
		
	}

	private void openSshSession() {
		
		if(sshConnection.isAuthenticationComplete()){
			try {
				// Open a session
				sshSession = sshConnection.openSession();
								
				// Used instead of executeCommand() because using of startShell()
				// in pair with writing commands to standard input 
				// allows to use environment variables and log in to MediationZone Shell
				sshSession.startShell();
				
			} catch (IOException e) {
				throw new RuntimeException("An exception occurred while trying to open session: Exception = " + e.getMessage(), e);
			}
		}else{
			throw new RuntimeException("You aren't logged in to any server");
		}
		
	}
	
	public void runCommand(String command) {
		if (sshSession != null){
			try {
				OutputStream stdIn = sshSession.getStdin();
				BufferedWriter shellInput = new BufferedWriter(new OutputStreamWriter(stdIn));

				shellInput.write("mzsh " + mzshLogin + "/" + mzshPwd + " " + command + "\n");
				shellInput.close();
				
				// Read the standard out
				StringBuilder commandOutput = new StringBuilder();				
				InputStream stdOut = new StreamGobbler(sshSession.getStdout());				
				BufferedReader shellOutput = new BufferedReader(new InputStreamReader(stdOut));
				
				String outLine = shellOutput.readLine();
				while( outLine != null )
				{
					commandOutput.append( outLine + "\n" );
					outLine = shellOutput.readLine();
				}
				shellOutput.close();

				// Read error out
				StringBuilder commandErrors = new StringBuilder();
				InputStream stdErr = new StreamGobbler(sshSession.getStderr());				
				BufferedReader shellErrors = new BufferedReader(new InputStreamReader(stdErr));
				String errLine = shellErrors.readLine();
				
				while( errLine != null )
				{
					commandErrors.append( errLine + "\n" );
				    errLine = shellErrors.readLine();
				}
				shellErrors.close();

				// DEBUG: dump the exit code
				System.out.println( "------\nExitCode: " + sshSession.getExitStatus() +"\n------" );

				// Close the session
				sshSession.close();
				
				// Print the results to the caller
				if (commandErrors.length() != 0){
					System.out.println(commandErrors.toString());
				}
				if (commandOutput.length() != 0) {
					System.out.println(commandOutput.toString());
				}
			} catch (Exception e) {
				throw new RuntimeException("An exception occurred while trying to execute the following command: " + command + ", Exception = " + e.getMessage(), e);
			}
		}
	}

	public void closeConnection() {
		if (sshSession != null) {
			sshSession.close();
		}

		if (sshConnection != null) {
			sshConnection.close();
		}
	}

}
