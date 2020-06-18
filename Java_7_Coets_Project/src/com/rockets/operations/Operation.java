package com.rockets.operations;

import com.rockets.project.Rocket;

import com.rockets.project.Thruster;

/**
 * Dades de la operaci� planificada amb les dades necess�ries per a executar a posteriori 
 * concurrentment cada Thread de tipus ThrusterThread
 * 
 * @author Ivan
 *
 */
public class Operation {

	Rocket rocket;      // Coet al qual se li aplicar� una operaci�
	Thruster thruster;  // Propulsor al qual se li aplicar� una operaci�
	int objectivePower; // Potencia objectiu de la operaci� accelerar o frenar 
						// a la qual haur� d'arribar el propulsor
	
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
