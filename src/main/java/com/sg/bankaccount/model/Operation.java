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

}
