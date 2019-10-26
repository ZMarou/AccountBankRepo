package com.sg.bankaccount.service.impl.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.bankaccount.domain.Client;
import com.sg.bankaccount.infra.entities.ClientEntity;
import com.sg.bankaccount.infra.repository.ClientRepository;

@Component
public final class ClientMapper extends AbstractMapper<ClientEntity, Client> {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientEntity convertToEntity(Client domain) {
		if (domain == null)
			return null;
		ClientEntity entity = new ClientEntity();
		if(domain.getId() > 0) {			
			entity = clientRepository.findById(domain.getId()).orElse(new ClientEntity());
		}
		entity.setFirstName(domain.getFirstName());
		entity.setLastName(domain.getLastName());
		entity.setEmail(domain.getEmail());
		return entity;
	}

	@Override
	public Client convertToDomain(ClientEntity entity) {
		if (entity == null)
			return null;
		Client domain = new Client();
		domain.setId(entity.getId());
		domain.setFirstName(entity.getFirstName());
		domain.setLastName(entity.getLastName());
		domain.setEmail(entity.getEmail());
		return domain;
	}

}
