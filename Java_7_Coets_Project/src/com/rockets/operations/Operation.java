package com.rockets.operations;

import com.rockets.project.Rocket;
import com.rockets.project.Thruster;

public class Operation {

	Rocket rocket;      //Coet al qual se li aplicar� una operaci�
	Thruster thruster;  //Propulsor al qual se li aplicar� una operaci�
	int objectivePower; //Potencia objectiu de la operaci� accelerar o frenar
	
	public Operation(Thruster t){
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
	
	public Rocket getRocket() {
		return this.rocket;
	}
	
	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}
	
}
