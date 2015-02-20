package com.ssh.fw;

public class MZWorkflowConfigData {

	private String wfName;
	private String wfInstance;
	private String wfInputFolder;	
	private String wfOutputFolder;

	public void setWfName(String wfName) {
		this.wfName = wfName;
	}
	
	public String getWfInstance() {
		return wfInstance;
	}

	public void setWfInstance(String wfInstance) {
		this.wfInstance = wfInstance;
	}

	public void setWfInputFolder(String wfInputFolder) {
		this.wfInputFolder = wfInputFolder;
	}

	public void setWfOutputFolder(String wfOutputFolder) {
		this.wfOutputFolder = wfOutputFolder;
	}

	public String getWfName() {
		return wfName;
	}
	
	public String getWfInputFolder() {
		return wfInputFolder;
	}

	public String getWfOutputFolder() {
		return wfOutputFolder;
	}
	
	public MZWorkflowConfigData withWFName(String wfName) {
		this.wfName = wfName;
		return this;
	}
	
	public MZWorkflowConfigData withWFInputFolder(String wfInputPath) {
		wfInputFolder = wfInputPath;
		return this;
	}

	public MZWorkflowConfigData withWFOutputFolder(String wfOutputPath) {
		wfOutputFolder = wfOutputPath;
		return this;
	}	

	@Override
	public String toString() {
		return "MZWorkflowConfigData [wfName=" + wfName + ", wfInstance="
				+ wfInstance + ", wfInputFolder=" + wfInputFolder
				+ ", wfOutputFolder=" + wfOutputFolder + "]";
	}

}
