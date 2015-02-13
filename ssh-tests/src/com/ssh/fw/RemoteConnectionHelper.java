package com.ssh.fw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;


public class RemoteConnectionHelper extends BaseHelper {

	private Connection sshConnection;
	
	public RemoteConnectionHelper(ApplicationManager manager) {
		super(manager);
	}

	public RemoteConnectionHelper login(SSHConnectData connectData) {
		sshConnection = new Connection(connectData.host);
		
		try {			
			sshConnection.connect();
			
			boolean isConnected = sshConnection.authenticateWithPassword(connectData.login, connectData.password);
            System.out.println("------\nConnection result: " + isConnected + "\n------");
            
		} catch (IOException e) {
			throw new RuntimeException("An exception occurred while trying to connect to the host: " + connectData.host + ", Exception = " + e.getMessage(), e);
		}
		
		return this;		
	}

	public RemoteConnectionHelper runCommand(String command) {
		if(sshConnection.isAuthenticationComplete()){
			try {
				// Open a session
				Session sshSession = sshConnection.openSession();
				sshSession.requestPTY("xterm");
				
				// Execute the command
				sshSession.execCommand( command );
				
				// Read the results
				StringBuilder commandOutput = new StringBuilder();				
				InputStream stdOut = new StreamGobbler(sshSession.getStdout());				
				BufferedReader shellOutput = new BufferedReader(new InputStreamReader(stdOut));
				
				StringBuilder commandErrors = new StringBuilder();
				InputStream stdErr = new StreamGobbler(sshSession.getStderr());				
				BufferedReader shellErrors = new BufferedReader(new InputStreamReader(stdErr));
				
				String outLine = shellOutput.readLine();
				while( outLine != null )
				{
					commandOutput.append( outLine + "\n" );
					outLine = shellOutput.readLine();
				}
				
				String errLine = shellErrors.readLine();
				while( errLine != null )
				{
					commandErrors.append( errLine + "\n" );
				    errLine = shellErrors.readLine();
				}

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
			} catch (IOException e) {
				throw new RuntimeException("An exception occurred while trying to execute the following command: " + command + ", Exception = " + e.getMessage(), e);
			}
		}else{
			throw new RuntimeException("You aren't logged in to any server");
		}
		return this;
	}

	public void closeRemoteConnection() {
		if(sshConnection.isAuthenticationComplete()){
			sshConnection.close();
		}
	}

}
