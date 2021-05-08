package com.everis.account.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;

	//fetch recupera información de la bd y LAZY recupera de la bd solo lo que se necesita
	//La relación ManyToOne es vista desde el punto del Currency: Muchos Currency tiene un Account
	//JsonIgnore: esta anotación por su parte ignora este atributo y evita que se realice un bucle a la hora de hacer el llamado por el service
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Account account;
	
}
