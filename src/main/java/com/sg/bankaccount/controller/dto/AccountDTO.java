package com.sg.bankaccount.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

	private long id;
	private long clientId;
	private double balance;
	private double amountOverdraft;

	public AccountDTO(long id, long clientId, double balance, double amountOverdraft) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.balance = balance;
		this.amountOverdraft = amountOverdraft;
	}

}
