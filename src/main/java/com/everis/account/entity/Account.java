package com.everis.account.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "startdate")
	private Date startDate;

	//La relación OneToMany es vista desde el punto del Account: un Account tiene Muchos Currency
	//El mappedBy tiene que hacer referencia al atributo de la clase a la cual esta representado en este caso es "account"
	@OneToMany(mappedBy = "account")
	private List<Currency> currencies;

}
