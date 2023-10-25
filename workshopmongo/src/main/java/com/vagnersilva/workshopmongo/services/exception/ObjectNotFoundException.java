package com.vagnersilva.workshopmongo.services.exception;

//Classe que ira disparar minhas execeções

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
