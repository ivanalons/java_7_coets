package com.rockets.project;

import java.util.ArrayList;
import java.util.List;

import com.rockets.exceptions.RocketCodeException;

public class Rocket {
	
	private String code;
	private List<Integer> thrustersMaxPower;
	
	public Rocket(String code) throws Exception{
		this.code = code;
		this.thrustersMaxPower = new ArrayList<>();
		
		checkCode();
	}
	
	public Rocket(String code, int thrustersNumber) throws Exception{
		this.code=code;
		this.thrustersMaxPower = new ArrayList<>(thrustersNumber);
		
		for(int i=0;i<this.thrustersMaxPower.size();i++) {
			this.thrustersMaxPower.set(i, 0);
		}
		
		checkCode();
	}
	
	public int getThrustersNumber() {
		return this.thrustersMaxPower.size();
	}
	
	public void addThrusterMaxPower(int maxPower) {
		this.thrustersMaxPower.add(maxPower);
	}
	
	public void checkCode() throws Exception {
		if (this.code.length()!=8) throw new RocketCodeException("El codi del coet ha de tenir 8 caracters!.");
	}
		
	public String toString() {
		String output = this.code+": ";
		
		for(Integer i: this.thrustersMaxPower) {
			output+=i+",";
		}
		
		output+= "(" + this.getThrustersNumber() +" propulsors)";
		
		return output;
	}
	
}
