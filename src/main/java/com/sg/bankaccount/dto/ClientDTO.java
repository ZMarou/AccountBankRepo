package com.sg.bankaccount.dto;

import java.util.List;

import com.sg.bankaccount.model.GenericAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

	private long id;

	private String firstName;

	private String lastName;

	private String email;

	private List<GenericAccount> accounts;

	public ClientDTO(long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

}
