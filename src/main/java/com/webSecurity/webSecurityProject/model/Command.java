package com.webSecurity.webSecurityProject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Command {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	private String libelle;
	
	
	@ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
	private User client;
	
	
	public Command() {
		super();
	}
	public Command(Date date, User client) {
		super();
		this.date = date;
		this.client = client;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
