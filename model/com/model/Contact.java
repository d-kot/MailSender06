package com.model;

import java.io.Serializable;

public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	String name;
	String surname;
	String email;
	String info;

	public Contact (String name, String surname, String email, String info) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.info = info;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String toString() {
		
		return this.name + " " + this.surname + "  \t<" + this.email+ ">";
	}
}
