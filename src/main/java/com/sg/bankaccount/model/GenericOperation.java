package com.sg.bankaccount.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance
@Getter
@Setter
@NoArgsConstructor
public abstract class GenericOperation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long id;
	
	@OneToOne
	protected History history;

}
