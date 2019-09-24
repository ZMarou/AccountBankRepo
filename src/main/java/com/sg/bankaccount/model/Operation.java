package com.sg.bankaccount.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.sg.bankaccount.enums.OperationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Operation extends GenericOperation {

	@NotNull
	private OperationType operationType;

	@NotNull
	private double amount;

	public Operation(@NotNull OperationType operationType, @NotNull double amount) {
		super();
		this.operationType = operationType;
		this.amount = amount;
	}
	
	public Operation(@NotNull long id, @NotNull OperationType operationType, @NotNull double amount) {
		super();
		this.operationType = operationType;
		this.amount = amount;
		this.id = id;
	}
	
	

}
