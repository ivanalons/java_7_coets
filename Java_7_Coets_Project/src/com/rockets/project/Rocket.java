package com.rockets.project;

import com.rockets.exceptions.RocketCodeException;

public class Rocket {
	
	private String code;
	private int thrustersNumber;
	
	public Rocket() throws Exception{
		this.code="xxxxxxxx";
		this.thrustersNumber=0;
		checkCode();
	}
	
	public Rocket(String code, int thrustersNumber) throws Exception{
		this.code=code;
		this.thrustersNumber=thrustersNumber;
		checkCode();
	}
	
	public void checkCode() throws Exception {
		if (this.code.length()!=8) throw new RocketCodeException("El codi del coet ha de tenir 8 caracters!.");
	}
		
	public String toString() {
		return "Codi: "+this.code+" , Número de propulsors: "+this.thrustersNumber;
	}
	
}
