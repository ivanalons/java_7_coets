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
		// amb el número de Thread, el codi del coet i el numero de propulsor		
		super("THREAD: ["+ numberThread +"] COET: [" + rocket.getCode()+"] PROPULSOR: ["+(thruster.getNumber()+1)+"]");
		
		this.numberThread = numberThread;
		this.rocket = rocket;
		this.thruster = thruster;
		this.typeOp = typeOp;
		this.objectivePower = objectivePower;
		
	}
	
	/**
	 * Codi que executa cada Thread "ThrusterThread"
	 */
	@Override
	public void run() {
	
		if (typeOp==OperationsLauncher.CONST_ACCELERATE) {
			accelerateThruster("ACCELERANT");
		}else {
			brakeThruster("FRENANT");
		}
		
		this.operationFinished=true;
		
	} 

	/**
	 * Mètode executat si la operació era ACCELERAR
	 * Incrementa la potencia actual del propulsor fins que arribi a la potencia objectiu
	 * 
	 * @param strOp
	 */
	public void accelerateThruster( String strOp ) {
		
		
		
		while(this.thruster.getCurrentPower() < this.objectivePower) {
			
			this.thruster.accelerate(); // incrementa en una unitat la potencia actual del propulsor
			
			System.out.println(this.getName() + " [" + strOp + "] Potencia Actual = [ "+this.thruster.getCurrentPower()
								+ " ] Potencia Objectiu = ["+this.objectivePower+"]");
			try {
				Thread.sleep(50); // posem el Thread en espera per a visualitzar la concurrència de fils
				  				// i els missatges corresponents més lentament
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Mètode executat si la operació era FRENAR
	 * Decrementa la potencia actual del propulsor fins que arribi a la potencia objectiu
	 * 
	 * @param strOp
	 */
	public void brakeThruster(String strOp) {
		
		while(this.thruster.getCurrentPower() > this.objectivePower) {
			
			this.thruster.brake(); //decrementa en una unitat la potencia actual del propulsor
			
			System.out.println(this.getName() + " [" + strOp + "] Potencia Actual = [ "+this.thruster.getCurrentPower()
			+ " ] Potencia Objectiu = ["+this.objectivePower+"]");
			
			try {
				Thread.sleep(50); // posem el Thread en espera per a visualitzar la concurrència de fils
								  // i els missatges corresponents més lentament
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
