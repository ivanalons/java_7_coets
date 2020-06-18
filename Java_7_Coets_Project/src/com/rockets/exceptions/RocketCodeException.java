package com.rockets.exceptions;

/**
 * Exepcio disparada quan el codi d'un cohet no té 8 caracters
 * @author Ivan
 *
 */
public class RocketCodeException extends Exception{

	public RocketCodeException() {
		super();
	}
	
	public RocketCodeException(String message) {
		super(message);
	}

}
