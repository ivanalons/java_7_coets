package com.rockets.project;

import java.util.ArrayList;
import java.util.List;

import com.rockets.exceptions.RocketCodeException;

public class Rocket {
	
	private String code;
	private List<Thruster> thrustersList;
	
	public Rocket(String code) throws Exception{
		this.code = code;
		this.thrustersList = new ArrayList<>();
		
		checkCode();
	}
	
	public void checkCode() throws Exception {
		if (this.code.length()!=8) throw new RocketCodeException("El codi del coet ha de tenir 8 caracters!.");
	}
	
	public String getCode() {
		return this.code;
	}
	
	public List<Thruster> getThrusters() {
		return this.thrustersList;
	}
	
	public int getThrustersNumber() {
		return this.thrustersList.size();
	}
	
	public void addThrusterMaxPower(int maxPower) {
		Thruster thruster = new Thruster(maxPower);
		this.thrustersList.add(thruster);
	}
	
	public void brakeThruster(int thrusterNumber , int objectivePower) {
		
		Thruster t = this.thrustersList.get(thrusterNumber);
		
		if (t.getCurrentPower() < objectivePower) { //no es pot frenar
			System.out.println("No es pot frenar el propulsor" + (thrusterNumber+1) + " del coet "
		    + this.code + " perque (potencia objectiu > potencia actual)");
			return;
		}else if(objectivePower < 0) { //potencia objectiu ha de ser mes gran que zero
			System.out.println("No es pot frenar el propulsor" + (thrusterNumber+1) + " del coet "
				    + this.code + " perque (potencia objectiu < zero)");
			return;
		}
		
		// codi
		System.out.println("FRENA propulsor "+ (thrusterNumber+1) + " del coet "+this.code);
	}
	
	public void accelerateThruster(int thrusterNumber , int objectivePower) {
		
		Thruster t = this.thrustersList.get(thrusterNumber);
		
		if (t.getCurrentPower() > objectivePower) { //no es pot accelerar
			System.out.println("No es pot accelerar el propulsor" + (thrusterNumber+1) + " del coet "
				    + this.code + " perque (potencia objectiu < potencia actual)");
			return;
		}else if(objectivePower > t.getMaxPower()) { //la potencia objectiu no pot superar la potencia maxima
			System.out.println("No es pot accelerar el propulsor" + (thrusterNumber+1) + " del coet "
				    + this.code + " perque (potencia objectiu > potencia maxima)");
			return;
		}
		
		// codi
		System.out.println("ACCELERA propulsor "+ (thrusterNumber+1) + " del coet "+this.code);

	}
			
	public String toString() {
		String output = "Coet "+this.code+": ";
		
		for(int i=0;i < this.thrustersList.size();i++) {
			Thruster t = this.thrustersList.get(i);
			output += (i+1) +"=["+t.getCurrentPower() +"/" +t.getMaxPower()+"]";
			if (i < this.thrustersList.size()-1) output+=",";
		}
						
		return output;
	}
	
}
