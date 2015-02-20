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
import static ch.ethz.ssh2.ChannelCondition.*;

public class SSHConnectionHelper extends BaseHelper {

	private Connection sshConnection;
	
	public SSHConnectionHelper(ApplicationManager manager) {
		super(manager);
	}

	public SSHConnectionHelper login(SSHConnectData connectData) {
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

	public SSHConnectionHelper runCommand(String command) {
		if(sshConnection.isAuthenticationComplete()){
			try {
				// Open a session
				Session sshSession = sshConnection.openSession();
								
				// Used instead of executeCommand() because using of startShell()
				// in pair with writing commands to standard input 
				// allows to use environment variables and log in to MediationZone Shell
				sshSession.startShell();
						
				OutputStream stdIn = sshSession.getStdin();
				BufferedWriter shellInput = new BufferedWriter(new OutputStreamWriter(stdIn));
				shellInput.write(command + "\n");
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
			} catch (IOException e) {
				throw new RuntimeException("An exception occurred while trying to execute the following command: " + command + ", Exception = " + e.getMessage(), e);
			}
		}else{
			throw new RuntimeException("You aren't logged in to any server");
		}
		return this;
	}

	public SSHConnectionHelper runCommands(String[] commands) {
		if(sshConnection.isAuthenticationComplete()){
			try {
				// Open a session
				Session sshSession = sshConnection.openSession();
								
				// Used instead of executeCommand() because using of startShell()
				// in pair with writing commands to standard input 
				// allows to use environment variables and log in to MediationZone Shell
				sshSession.startShell();
						
				OutputStream stdIn = sshSession.getStdin();
				BufferedWriter shellInput = new BufferedWriter(new OutputStreamWriter(stdIn));
				
				for (String command : commands) {
					shellInput.write(command + "\n");
					
					try {
						Thread.sleep(7000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
								
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
			} catch (IOException e) {
				throw new RuntimeException("An exception occurred while trying to execute command: Exception = " + e.getMessage(), e);
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
