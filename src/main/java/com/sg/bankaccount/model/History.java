package com.sg.bankaccount.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class History {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@OneToOne
	private GenericOperation operation;

	@NotNull
	private Date date;

	@NotNull
	private double balance;
	
	@ManyToOne
	private GenericAccount account;

	public History(long id, GenericOperation operation, @NotNull Date date, @NotNull double balance) {
		super();
		this.id = id;
		this.operation = operation;
		this.date = date;
		this.balance = balance;
	}
	
	

}
