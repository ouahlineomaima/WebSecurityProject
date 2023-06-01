package com.webSecurity.webSecurityProject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
//@Table(name = "uusers")
public class User {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String nom;
	private String prenom;
	private int age;
	
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Command> commands;
	
	public User() {
		super();
	}
	public User(String nom, String prenom, int age) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
