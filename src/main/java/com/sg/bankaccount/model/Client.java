package com.sg.bankaccount.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String email;

	@OneToMany
	private List<GenericAccount> accounts;

	public Client(@NotNull String firstName, @NotNull String lastName, @NotNull String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accounts = new ArrayList<>();
	}

}
