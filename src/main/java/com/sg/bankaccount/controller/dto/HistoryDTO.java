package com.sg.bankaccount.controller.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDTO {

	private String operation;
	private Date date;
	private double amount;
	
	
}
