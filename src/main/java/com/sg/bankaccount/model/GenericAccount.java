package com.sg.bankaccount.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance
@Getter
@Setter
public abstract class GenericAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long id;

	@ManyToOne
	protected Client client;

	@OneToMany
	protected List<History> history;
	
	protected double balance;
}
