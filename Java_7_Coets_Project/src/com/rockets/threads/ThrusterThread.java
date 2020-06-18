package com.rockets.threads;

import com.rockets.project.Rocket;
import com.rockets.project.Thruster;

public class ThrusterThread extends Thread implements Runnable {
	
	private int numberThread;
	private Rocket rocket;
	private Thruster thruster;
	private int typeOp;
	private int objectivePower;
	private boolean operationFinished = false;
	
	public ThrusterThread() {
		super();
	}
	
	public ThrusterThread(int numberThread, Rocket rocket, Thruster thruster, int typeOp, int objectivePower) {
		// Cridem al constructor de la superclasse Thread i li assignem un nom 
		// pel coet i el numero de propulsor
		super("THREAD: "+ numberThread +" COET: " + rocket.getCode()+" PROPULSOR: [ "+(thruster.getNumber()+1)+" ]");
		
		this.rocket = rocket;
		this.thruster = thruster;
		this.typeOp = typeOp;
		this.objectivePower = objectivePower;
		
	}
	
	@Override
	public void run() {
	
		if (typeOp==OperationsLauncher.CONST_ACCELERATE) {
			accelerateThruster();
		}else {
			brakeThruster();
		}
		
		this.operationFinished=true;
		
	} 

	public void accelerateThruster() {
		
		while(this.thruster.getCurrentPower() < this.objectivePower) {
			
			this.thruster.accelerate();
			
			System.out.println(this.getName() + " Potencia Actual="+this.thruster.getCurrentPower()
								+ " Potencia Objectiu="+this.objectivePower);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void brakeThruster() {
		
		while(this.thruster.getCurrentPower() > this.objectivePower) {
			
			this.thruster.brake();
			
			System.out.println(this.getName() + " Potencia Actual="+this.thruster.getCurrentPower()
			+ " Potencia Objectiu="+this.objectivePower);
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isOperationFinished() {
		return this.operationFinished;
	}
}
