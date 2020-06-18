package com.rockets.operations;

import com.rockets.project.Rocket;

import com.rockets.project.Thruster;

/**
 * Dades de la operació planificada amb les dades necessàries per a executar a posteriori 
 * concurrentment cada Thread de tipus ThrusterThread
 * 
 * @author Ivan
 *
 */
public class Operation {

	Rocket rocket;      // Coet al qual se li aplicarà una operació
	Thruster thruster;  // Propulsor al qual se li aplicarà una operació
	int objectivePower; // Potencia objectiu de la operació accelerar o frenar 
						// a la qual haurà d'arribar el propulsor
	
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
