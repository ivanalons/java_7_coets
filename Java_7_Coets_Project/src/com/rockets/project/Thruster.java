package com.rockets.project;

public class Thruster {

	private int maxPower;
	private int currentPower = 0;
	
	public Thruster(int maxPower) {
		this.maxPower=maxPower;
		this.currentPower=0;
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
	
	public String toString() {
		String output ="";
		output+= "[ Potencia Actual: "+ this.currentPower +" / Potencia maxima: "+ this.maxPower+" ]";
		return output;
	}
	
}
