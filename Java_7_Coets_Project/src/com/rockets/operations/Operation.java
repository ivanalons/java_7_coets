package com.rockets.operations;

import com.rockets.project.Thruster;

public class Operation {

	Thruster thruster;
	int objectivePower;
	
	Operation(Thruster t){
		this.thruster=t;
	}

	public Thruster getThruster() {
		return thruster;
	}

	public int getObjectivePower() {
		return objectivePower;
	}

	public void setObjectivePower(int objectivePower) {
		this.objectivePower = objectivePower;
	}
	
	
}
