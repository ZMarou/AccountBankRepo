package com.sg.bankaccount.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDTO {

	private long idHistory;
	private String operation;
	private Date date;
	private double amount;
	private double balance;
	
	public HistoryDTO(long idHistory, String operation, Date date, double amount, double balance) {
		super();
		this.idHistory = idHistory;
		this.operation = operation;
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}
	
	
}
