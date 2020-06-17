package com.rockets.main;

import com.rockets.project.Rocket;

public class Main {

	public static void main(String[] args) {

		fase2();
		
	}

	public static void fase2() {
		
		System.out.println("------");
		System.out.println("FASE 2");
		System.out.println("------");
		
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
			
			System.out.println("COET 1: "+rocket1.toString());
			System.out.println("COET 2: "+rocket2.toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
