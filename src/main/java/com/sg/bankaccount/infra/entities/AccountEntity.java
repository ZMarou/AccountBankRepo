package com.sg.bankaccount.infra.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long id;

	@Version
	private long version;

	@ManyToOne(fetch = FetchType.EAGER)
	protected ClientEntity client;
	
	protected double balance;
	
	private double amountOverdraft;

	public AccountEntity(long id, ClientEntity client, double balance, double amountOverdraft) {
		super();
		this.id = id;
		this.client = client;
		this.balance = balance;
		this.amountOverdraft = amountOverdraft;
	}
	
	

}
