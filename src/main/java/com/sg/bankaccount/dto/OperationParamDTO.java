package com.sg.bankaccount.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OperationParamDTO {

	private double amount;
	private long idClient;
	private long idAccount;

	public OperationParamDTO(long idClient, long idAccount, double amount) {
		super();
		this.idClient = idClient;
		this.idAccount = idAccount;
		this.amount = amount;
	}

}
