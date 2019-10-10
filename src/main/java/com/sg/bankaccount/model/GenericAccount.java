package com.sg.bankaccount.model;

import java.util.List;

import javax.persistence.*;

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

	@Version
	private long version;

	@ManyToOne
	protected Client client;

	@OneToMany
	protected List<History> history;
	
	protected double balance;
}
