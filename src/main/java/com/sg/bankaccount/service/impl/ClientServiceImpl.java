package com.sg.bankaccount.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.bankaccount.domain.Client;
import com.sg.bankaccount.domain.service.ClientService;
import com.sg.bankaccount.infra.entities.ClientEntity;
import com.sg.bankaccount.infra.repository.ClientRepository;
import com.sg.bankaccount.service.impl.mapper.ClientMapper;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientMapper clientMapper;

	@Override
	public Client save(Client client) {
		ClientEntity clientEntity = clientMapper.convertToEntity(client);
		ClientEntity clientCreated = clientRepository.save(clientEntity);
		return clientMapper.convertToDomain(clientCreated);
	}

	@Override
	public Client validateClient(long clientId) {
		if (clientId != 0) {
			Optional<ClientEntity> optionalClient = clientRepository.findById(clientId);
			ClientEntity clientCreated = optionalClient.orElse(null);
			return clientCreated != null
					? clientMapper.convertToDomain(clientCreated)
					: null;
		}
		return null;
	}

}
