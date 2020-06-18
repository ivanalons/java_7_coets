package com.rockets.main;

import java.util.ArrayList;
import java.util.List;

import com.rockets.project.Rocket;
import com.rockets.ui.InputManager;

public class Main {

	public static void main(String[] args) {

		List<Rocket> rockets = initRockets(); //Llista amb els coets inicialitzats segons l'enunciat de l'exercici
		
		InputManager ui = new InputManager(rockets); //Encapsula la gestió d'entrada de dades
		
		ui.showMenu(); 		//Es mostra el menu principal
		
		ui.close(); //Es tanca l'objecte Scanner de gestio d'entrada de dades que encapsula la classe InputManager
	}

	// S'inicialitzen els coets sense demanar les dades per consola tal i com s'indica a l'enunciat 
	// i es retornen en una llista
	public static List<Rocket> initRockets() {
		
		List<Rocket> list = new ArrayList<Rocket>();
		
		try {
			Rocket rocket1 = new Rocket("32WESSDS");
			Rocket rocket2 = new Rocket("LDSFJA32");
			
			rocket1.addThrusterMaxPower(10);
			rocket1.addThrusterMaxPower(30);
			rocket1.addThrusterMaxPower(80);
			
			rocket2.addThrusterMaxPower(30);
			rocket2.addThrusterMaxPower(40);
			rocket2.addThrusterMaxPower(50);
			rocket2.addThrusterMaxPower(50);
			rocket2.addThrusterMaxPower(30);
			rocket2.addThrusterMaxPower(10);
			
			list.add(rocket1);
			list.add(rocket2);
			
			//System.out.println("COET 1: "+rocket1.toString());
			//System.out.println("COET 2: "+rocket2.toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
		
	}
	
}
