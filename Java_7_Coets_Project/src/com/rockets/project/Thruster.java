package com.rockets.project;

public class Thruster {

	private int maxPower;
	private int currentPower = 0;
	private int number;
	
	public Thruster(int maxPower, int number) {
		this.maxPower=maxPower;
		this.currentPower=0;
		this.number = number;
	}

	public int getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(int currentPower) {
		this.currentPower = currentPower;
	}

	public int getMaxPower() {
		return maxPower;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String toString() {
		String output ="";
		output+= "[ Potencia Actual: "+ this.currentPower +" / Potencia maxima: "+ this.maxPower+" ]";
		return output;
	}
	
}
