package com.sg.bankaccount.dto;

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
	private double amountDiscovered;

	public AccountDTO(long id, long clientId, double balance, double amountDiscovered) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.balance = balance;
		this.amountDiscovered = amountDiscovered;
	}

}
