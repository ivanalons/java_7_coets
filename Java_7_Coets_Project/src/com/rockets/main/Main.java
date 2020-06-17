package com.rockets.main;

import com.rockets.project.Rocket;

public class Main {

	public static void main(String[] args) {

		fase1();
		
	}

	public static void fase1() {
		
		System.out.println("------");
		System.out.println("FASE 1");
		System.out.println("------");
		
		try {
			Rocket rocket1 = new Rocket("xxxxxxxx",3);
			Rocket rocket2 = new Rocket("LDSFJA32",6);

			System.out.println("COET 1: "+rocket1.toString());
			System.out.println("COET 2: "+rocket2.toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
