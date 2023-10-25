package com.vagnersilva.palindromo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Coleção Palindrmo MongoDB
@Document
public class Palindromo {

	@Id
	private String id;
	private String value;

	public Palindromo() {
	}

	public Palindromo(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
