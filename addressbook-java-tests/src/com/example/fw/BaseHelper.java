package com.example.fw;

import javax.swing.JDialog;

import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;

public class BaseHelper {
	
	protected final ApplicationManager manager;
	protected final JFrameOperator mainFrame;

	public BaseHelper(ApplicationManager applicationManager) {
		this.manager = applicationManager;
		this.mainFrame = manager.getApplication();
	}
	
	protected String waitMessageDialog(String title, int timeout) {
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		
		while (currTime < startTime + timeout){
			
			JDialog jdialog = JDialogOperator.findJDialog(mainFrame.getOwner(), title, false, false);
			
			if (jdialog != null){
				JDialogOperator jdialOp = new JDialogOperator(jdialog);
				String msg = new JLabelOperator(jdialOp).getText();
				jdialOp.requestClose();
				return msg;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			currTime = System.currentTimeMillis();			
		}
		
		return null;
	}

}
