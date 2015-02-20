package com.ssh.fw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MZTargetSubsystems {

	private HashMap<String, List<MZWorkflowConfigData>> workflows;
	
	public MZTargetSubsystems() {
		workflows = new HashMap<String, List<MZWorkflowConfigData>>();
	}
	
	public void addWorkflow(String subsystem, MZWorkflowConfigData workflow) {
		if (workflows.containsKey(subsystem)){
			workflows.get(subsystem).add(workflow);
		}else{
			List<MZWorkflowConfigData> list = new ArrayList<MZWorkflowConfigData>();
			list.add(workflow);
			workflows.put(subsystem, list);
		}
		
	}
		
	public ArrayList<MZWorkflowConfigData> getWorkflows(String subsystem) {
		return (ArrayList)workflows.get(subsystem);
	}
	
	/*@Override
	public String toString() {
		return "MZTargetSubsystem [subsystemName=" + subsystemName
				+ ", workflows=" + workflows + "]";
	}*/

}
