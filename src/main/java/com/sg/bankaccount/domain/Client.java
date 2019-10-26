package com.sg.bankaccount.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sg.bankaccount.controller.dto.ClientDTO;
import com.sg.bankaccount.domain.api.ClientAPI;
import com.sg.bankaccount.domain.exception.BusinessException;
import com.sg.bankaccount.domain.service.ClientService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class Client implements ClientAPI {

	private long id;

	private String firstName;

	private String lastName;

	private String email;

	@Autowired
	private ClientService clientService;

	public Client(long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Client(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public Client addClient(ClientDTO client) throws BusinessException {
		if(!isValidFields(client.getFirstName(), client.getLastName(), client.getEmail())) {
			throw new BusinessException("Invalid field");
		}
		Client clientCreated = clientService.save(new Client(client.getFirstName(), client.getLastName(), client.getEmail()));
		return clientCreated;
	}

	private boolean isValidFields(String firstName, String lastName, String email) {
		if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(email)) {
			return false;
		}
		return true;
	}

}
