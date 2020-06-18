package com.rockets.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputCommons {

	Scanner input;
	
	public InputCommons(Scanner scanner) {
		this.input=scanner;
	}
	
	/**
	 * Metode reutilitzable per a demanar a l'usuari introduir un STRING per consola
	 * Previament mostra per consola el missatge passat per parametre 
	 * 
	 * @param questionMessage
	 * @return
	 */
	public String askString(String questionMessage) {
		
		System.out.println(questionMessage);
		String answer = this.input.nextLine();
		
		return answer;
	}
	
	/**
	 * Es demana un número de tipus enter per consola
	 * Si no té un format de tipus enter vàlid o el número no es troba dins del rang dels 
	 * parametres "minInt" i "maxInt" s'informa de l'error per pantalla i es torna a demanar
	 * un número a l'usuari
	 */
	public int askOption(int minInt, int maxInt) {
		
		return askInt("Selecciona una opció:",minInt,maxInt);
		
	}
	
	/**
	 * Es demana un número de tipus enter per consola
	 * Previament es mostra el missatge passat per parametre "questionMessage"
	 * Si no té un format de tipus enter vàlid o el número no es troba dins del rang dels 
	 * parametres "minInt" i "maxInt" s'informa de l'error per pantalla i es torna a demanar
	 * un número a l'usuari
	 * 
	 * @param questionMessage
	 * @param minInt
	 * @param maxInt
	 * @return
	 */
	public int askInt(String questionMessage,int minInt, int maxInt) {
		
		int option= 0;
		boolean numberFormat = false;
		
		System.out.println(questionMessage);
		
		while(numberFormat==false) {
			try {
				
				option = this.input.nextInt();
				if(option>=minInt && option<=maxInt) {
					numberFormat=true;
				}else {
					System.out.println("Tria una opció introduint un número entre "+minInt+ " i "+maxInt);
				}
				
			}catch(InputMismatchException e) {
				
				System.out.println("Tria una opció introduint un número entre "+minInt+ " i "+maxInt);
				
			}
			this.input.nextLine();
		}
		
		return option;
	}
	
	/**
	 *  Es fa una pausa per consola per a poder veure els últims missatges fins que l'usuari
	 *  premi la tecla ENTER per consola
	 *  
	 */
	
	public void pause() {
		System.out.println("Prem la tecla ENTER/INTRO per a tornar al menu...");
		this.input.nextLine();
	}
	
}
