package com.rockets.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rockets.operations.Accelerate;
import com.rockets.operations.Brake;
import com.rockets.operations.Operation;
import com.rockets.project.Rocket;
import com.rockets.project.Thruster;
import com.rockets.threads.OperationsLauncher;

public class InputManager {

	Scanner input; //objecte per a capturar entrada de dades per consola
	InputCommons commons; //objecte que encapsula la operativa d'entrada de dades per consola
	
	List<Rocket> rocketsList; //llista de coets ja inicialitzada al parametre del constructor
	List<Operation> operationsList; //llista d'operacions a realitzar concurrentment
	
	public InputManager(List<Rocket> rocketsList){
		
		this.input = new Scanner(System.in);
		this.commons = new InputCommons(this.input);

		this.operationsList = new ArrayList<>();
		this.rocketsList = rocketsList;
	}
	
	public void close() {
		this.input.close();
	}
	
	//mostra menu per pantalla
	public void showMenu() {
		
		boolean exit = false;
		int option = 0;
		
		while (exit==false) {
			
			System.out.println("-----------------------------------");
			System.out.println("	            MENU               ");		
			System.out.println("-----------------------------------");

			System.out.println("1. Mostra estat propulsors coets.");
			System.out.println("2. Planifica operacio.");
			System.out.println("3. Executa operacions planificades.");
			System.out.println("4. Esborra operacions planificades.");
			System.out.println("5. Sortir del programa.");

			
			System.out.println("-----------------------------------");
			
			if (this.operationsList.size()>0) {
				this.showScheduledOperations();
			}else {
				System.out.println("Encara no hi ha cap operació planificada!");
			}
			
			System.out.println("-----------------------------------");

			
			option = commons.askOption(1, 5);
			
			if(option == 1 ) {
				this.showRocketsState();
			}else if(option == 2) {
				this.scheduleOperation();
			}else if(option == 3) {
				this.executeOperations();
			}else if(option == 4) {
				this.removeAllOperations();
			}
			
			
			exit = (option==5);
		}

	}
	
	/**
	 * Mostra per pantalla l'estat de tots els coets, indicant el seu codi, i per a cada coet, 
	 * es mostra la llista de propulsors amb la seva potencia actual i la seva potencia maxima
	 */
	public void showRocketsState() {
		
		System.out.println("SINTAXIS: Número propulsor = [ potencia actual / potencia maxima ]");

		System.out.println();
		
		for(Rocket r : this.rocketsList) {
			System.out.println(r.toString());
		}
		
		System.out.println();
		
		commons.pause();
		
	}
	
	/**
	 * Crea una operació que contindrà el coet, el propulsor, el tipus d'operació (accelerar o frenar)
	 * , així com la potencia objectiu que el thread corresponent haurà d'arribar
	 */
	public void scheduleOperation() {
		
		System.out.println("---------------------");
		System.out.println("PLANIFICAR OPERACIONS");
		System.out.println("---------------------");

		Rocket rocket = this.chooseRocket(); //selecciona coet per consola
		Thruster thruster = this.chooseThruster(rocket); //selecciona propulsor per consola
		Operation op = this.chooseOperation(thruster); //selecciona tipus d'operació per consola
		op.setRocket(rocket); //s'afegeix el coet Rocket a la operació per a poder saber el codi del coet
		this.chooseObjectivePower(op); //selecciona potència objectiu per consola
		
		if (this.conflictBetweenOperations(op)==false) {
			operationsList.add(op); //afegeix operació a la llista d'operacions
		}else {
			System.out.println("No es possible planificar realitzar 2 operacions sobre un mateix propulsor d'un coet!");
		}
		
		commons.pause();
	}
	
	/**
	 * Es mostren per pantalla tots els coets disponibles i es demana a l'usuari que seleccioni un
	 * @return objecte coet Rocket seleccionat per l'usuari
	 */
	private Rocket chooseRocket() {
		
		System.out.println("------------");
		System.out.println("Llista coets");
		System.out.println("------------");
		
		for(int i=0;i<this.rocketsList.size();i++) {
			System.out.println((i+1) +". "+ this.rocketsList.get(i).getCode());
		}
		
		System.out.println("------------");

		int indexRocket = commons.askInt("Selecciona coet:",1, this.rocketsList.size()) - 1;
		
		Rocket rocket = this.rocketsList.get(indexRocket);

		return rocket;
	}
	
	/**
	 * Es mostra per pantalla tots els propulsors del coet indicat per paràmetre i es demana a l'usuari
	 * que seleccioni un
	 * 
	 * @param rocket
	 * @return objecte propulsor Thruster seleccionat per l'usuari
	 */
	private Thruster chooseThruster(Rocket rocket) {
		
		System.out.println("--------------------------------------");
		System.out.println("Llista propulsors del coet " + rocket.getCode());
		System.out.println("--------------------------------------");
		
		List<Thruster> thrustersList = rocket.getThrusters();
		
		for(int i=0;i<thrustersList.size();i++) {
			System.out.println((i+1) +". "+thrustersList.get(i).toString());
		}
		System.out.println("--------------------------------------");

		int indexRocket = commons.askInt("Selecciona propulsor:",1, thrustersList.size()) - 1;
		
		Thruster thruster = thrustersList.get(indexRocket);
		
		return thruster;

	}
	
	/**
	 * Es demana a l'usuari si vol accelerar o frenar el propulsor
	 * @param thruster
	 * @return es retorna un objecte Operation del tipus Accelerate o Brake segons hagi seleccionat 
	 *         l'usuari
	 */
	private Operation chooseOperation(Thruster thruster) {
		
		System.out.println("--------------------");
		System.out.println("Seleccionar operació");
		System.out.println("--------------------");
	
		
		int operation = commons.askInt("Selecciona operació: (1:accelerar/2:frenar)", 1, 2);
		
		Operation op = null;
		
		if (operation==1) {
			op=new Accelerate(thruster);
		}else if(operation==2) {
			op=new Brake(thruster);
		}
		
		return op;
	}
	
	/**
	 * Es demana a l'usuari per consola que introdueixi la potencia objectiu
	 * Es torna a demanar si hi ha una incongruencia segons el tipus d'operació accelerar o frenar 
	 * @param op
	 */
	private void chooseObjectivePower(Operation op) {
	
		//boolean correctOperation=false, correctAccelerate, correctBrake;
		Thruster thruster = op.getThruster();
		int operation = 0;
		int objectivePower = 0;
		
		//while(correctOperation==false) {
			
			if(op instanceof Accelerate) {
				operation=1;
			}else {
				operation=2;
			}
			
			if (operation==1) {
				objectivePower = commons.askInt("Introdueix la potencia objectiu:", thruster.getCurrentPower(), thruster.getMaxPower());
			} else if (operation==2){
				objectivePower = commons.askInt("Introdueix la potencia objectiu:", 0, thruster.getCurrentPower());
			}
		/*	
			correctAccelerate = (objectivePower>=thruster.getCurrentPower() && objectivePower<=thruster.getMaxPower());
			correctBrake = (objectivePower<=thruster.getCurrentPower() && objectivePower>=0);
			
			correctOperation = (operation==1 && correctAccelerate) || (operation==2 && correctBrake);
		}*/
		
		op.setObjectivePower(objectivePower);
	}
	
	/** 
	 * Mostra totes les operacions planificades per pantalla
	 */
	public void showScheduledOperations() {
		
		System.out.println("OPERACIONS PLANIFICADES:");
		System.out.println("----------------------------------");

		String output = "";
		
		for (Operation op : this.operationsList) {
			
			boolean accelerate = op instanceof Accelerate;
			
			Thruster t = op.getThruster();
			Rocket r = op.getRocket();
			int objectivePower = op.getObjectivePower();
			
			if(accelerate) {
				output = "ACCELERAR el propulsor "+(t.getNumber()+1)+ " del coet "+r.getCode()+ 
						 " de la potencia actual: "+ t.getCurrentPower() + " a la potencia objectiu: "+
						 objectivePower;
			}else {
				output = "FRENAR el propulsor "+(t.getNumber()+1)+ " del coet "+r.getCode()+ 
						 " de la potencia actual: "+ t.getCurrentPower() + " a la potencia objectiu: "+
						 objectivePower;
			}
			System.out.println(output);
		}
	}

	/**
	 * no poden haver 2 operacions planificades per a un mateix propulsor
	 * retorna true si hi ha aquest conflicte i false en cas contrari
	 * @param paramOperation
	 * @return
	 */
	private boolean conflictBetweenOperations(Operation paramOperation) {
		
		boolean conflict = false;
		
		String p_codeRocket = paramOperation.getRocket().getCode();
		int p_thrusterNumber = paramOperation.getThruster().getNumber();

		
		for (Operation op : this.operationsList) {
			String codeRocket = op.getRocket().getCode();
			int thrusterNumber = op.getThruster().getNumber();

			conflict = (p_codeRocket.equals(codeRocket)) && (p_thrusterNumber == thrusterNumber);
			if (conflict==true) return true;
		}
		
		return false;
	}
	
	public void removeAllOperations() {
		this.operationsList.removeAll(this.operationsList);
		System.out.println("Totes les operacions planificades s'han eliminat correctament.");
		commons.pause();
	}
	
	public void executeOperations() {
		
		OperationsLauncher launcher = new OperationsLauncher(this.operationsList);
		
		try {
			launcher.execute();
			
			while (launcher.areAllOperationsFinished()==false) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.operationsList.removeAll(this.operationsList);
			
			System.out.println("Totes les operacions planificades s'han executat correctament");
			
		}finally {
			launcher.stopAllThreads();
		}
		
		commons.pause();
	}
}
