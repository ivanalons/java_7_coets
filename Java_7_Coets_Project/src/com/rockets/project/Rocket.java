package com.rockets.project;

import java.util.ArrayList;
import java.util.List;

import com.rockets.exceptions.RocketCodeException;

// Encapsula un objecte que representa un coet
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
	
	// Afegeix un nou propulsor a l'objecte coet amb maxima potencia indicada per parametre
	public void addThrusterMaxPower(int maxPower) {
		
		// Se li passa per parametre al constructor del propulsor Thruster :
		// this.thrustersList.size() : la seva posicio dins de la llista "thrustersList" del coet
		// per a saber quin és el número de propulsor d'entre tots els propulsors del coet Rocket
		Thruster thruster = new Thruster(maxPower,this.thrustersList.size());
		this.thrustersList.add(thruster);
	}
	
	//Els threads ja saben quin propulsor del coet i potencia objectiu han d'assolir 
	//per tant s'accelera o frena cada propulsor independentment, no cal cridar als metodes de la
	//classe Rocket, es criden als metodes de la classe propulsor Thruster directament
	
	/*
	public void brakeThruster(int thrusterNumber , int objectivePower) {
		
		Thruster t = this.thrustersList.get(thrusterNumber);
		t.brake();
		
	}
	
	public void accelerateThruster(int thrusterNumber , int objectivePower) {
		
		Thruster t = this.thrustersList.get(thrusterNumber);
		t.accelerate();

	}*/
			
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
