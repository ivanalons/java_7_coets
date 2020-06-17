package com.rockets.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rockets.operations.Accelerate;
import com.rockets.operations.Brake;
import com.rockets.operations.Operation;
import com.rockets.project.Rocket;
import com.rockets.project.Thruster;

public class InputManager {

	Scanner input;
	InputCommons commons;
	
	List<Rocket> rocketsList;
	List<Operation> operationsList;
	
	public InputManager(List<Rocket> rocketsList){
		
		this.input = new Scanner(System.in);
		this.commons = new InputCommons(this.input);

		this.operationsList = new ArrayList<>();
		this.rocketsList = rocketsList;
	}
	
	public void close() {
		this.input.close();
	}
	
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
			
			//this.showScheduledOperations();
			
			System.out.println("-----------------------------------");

			
			option = commons.askOption(1, 5);
			
			if(option == 1 ) {
				this.showRocketsState();
			}else if(option == 2) {
				this.scheduleOperation();
			}
			
			
			exit = (option==5);
		}

	}
		
	public void showRocketsState() {
		
		System.out.println("SINTAXIS: Número propulsor = [ potencia actual / potencia maxima ]");

		System.out.println();
		
		for(Rocket r : this.rocketsList) {
			System.out.println(r.toString());
		}
		
		System.out.println();
		
		commons.pause();
		
	}
	
	public void scheduleOperation() {
		
		System.out.println("---------------------");
		System.out.println("PLANIFICAR OPERACIONS");
		System.out.println("---------------------");

		Rocket rocket = this.chooseRocket();
		Thruster thruster = this.chooseThruster(rocket);
		Operation op = this.chooseOperation(thruster);
		this.chooseObjectivePower(op);
		
	}
	
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
	
	private void chooseObjectivePower(Operation op) {
	
		boolean correctOperation=false, correctAccelerate, correctBrake;
		Thruster thruster = op.getThruster();
		int operation = 0;
		int objectivePower = 0;
		
		while(correctOperation==false) {
			
			objectivePower = commons.askInt("Introdueix la potencia objectiu:", 0, thruster.getMaxPower());
			
			correctAccelerate = (objectivePower>=thruster.getCurrentPower() && objectivePower<=thruster.getMaxPower());
			correctBrake = (objectivePower<=thruster.getCurrentPower() && objectivePower>=0);
			
			if(op instanceof Accelerate) {
				operation=1;
			}else {
				operation=2;
			}
			
			if (operation==1 && correctAccelerate==false) 
				System.out.println("No es pot accelerar a una potencia inferior a l'actual (" 
								   + thruster.getCurrentPower() + ")");
			if (operation==2 && correctBrake==false) 
				System.out.println("No es pot frenar a una potencia superior a l'actual ("
								   + thruster.getCurrentPower() + ")");
			
			correctOperation = (operation==1 && correctAccelerate) || (operation==2 && correctBrake);
		}
		
		op.setObjectivePower(objectivePower);
	}
	
}
