package com.sg.bankaccount.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.bankaccount.dto.ClientDTO;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.repository.ClientRepository;
import com.sg.bankaccount.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientDTO create(ClientDTO dto) {
		Client clientCreated = clientRepository.save(new Client(dto.getFirstName(), dto.getLastName(), dto.getEmail()));
		return new ClientDTO(clientCreated.getId(), clientCreated.getFirstName(), clientCreated.getLastName(),
				clientCreated.getEmail());
	}

}
