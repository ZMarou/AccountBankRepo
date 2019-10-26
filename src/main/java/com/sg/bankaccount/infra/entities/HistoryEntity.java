package com.sg.bankaccount.infra.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.sg.bankaccount.domain.enums.OperationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HistoryEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull
	private OperationType operation;

	@NotNull
	private Date date;

	@NotNull
	private double amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private AccountEntity account;

	
	
	

}
