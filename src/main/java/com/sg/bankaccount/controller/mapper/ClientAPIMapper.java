package com.sg.bankaccount.controller.mapper;

import com.sg.bankaccount.controller.dto.ClientDTO;
import com.sg.bankaccount.domain.Client;

public class ClientAPIMapper extends AbstractAPIMapper<Client, ClientDTO>{

	@Override
	public ClientDTO convertToDTO(Client domain) {
		ClientDTO dto = new ClientDTO();
		dto.setId(domain.getId());
		dto.setFirstName(domain.getFirstName());
		dto.setLastName(domain.getLastName());
		dto.setEmail(domain.getEmail());
		return dto;
	}

}
