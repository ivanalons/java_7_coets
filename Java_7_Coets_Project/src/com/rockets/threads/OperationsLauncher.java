package com.rockets.threads;

import java.util.ArrayList;
import java.util.List;

import com.rockets.operations.Accelerate;
import com.rockets.operations.Operation;

public class OperationsLauncher {

	private List<ThrusterThread> threadsList;
	
	public static final int CONST_ACCELERATE = 1;
	public static final int CONST_BRAKE = 2;

	
	public OperationsLauncher(List<Operation> opsList) {
		
		threadsList = new ArrayList<>();
		int typeOp=0;
		int i=0;
		
		for (Operation op: opsList) {
			
			if (op instanceof Accelerate) {
				typeOp = CONST_ACCELERATE;
			}else {
				typeOp = CONST_BRAKE;
			}
			
			ThrusterThread thread = 
					new ThrusterThread(i,op.getRocket(),op.getThruster(),typeOp, op.getObjectivePower());
			
			this.threadsList.add(thread);
			i++;
		}
		
	}
	
	public void execute() {
		
		for (ThrusterThread t : this.threadsList) {
			t.start();
		}
	}
	
	public boolean areAllOperationsFinished() {
		
		boolean finished = true;
		int i=0;
		
		while(i<this.threadsList.size() && finished==true) {
			
			 finished = this.threadsList.get(i).isOperationFinished();
			 i++;
		}		
		
		return finished;
	}
}
