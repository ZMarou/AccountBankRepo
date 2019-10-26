package com.sg.bankaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.bankaccount.controller.dto.ClientDTO;
import com.sg.bankaccount.controller.mapper.ClientAPIMapper;
import com.sg.bankaccount.domain.Client;
import com.sg.bankaccount.domain.api.ClientAPI;
import com.sg.bankaccount.domain.exception.BusinessException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

	@Autowired
	private ClientAPI clientAPI;

	@ApiOperation(value = "Create client")
	@PostMapping
	public ResponseEntity<Object> createClient(@RequestBody ClientDTO dto) {
		Client clientCreated;
		try {
			clientCreated = clientAPI.addClient(dto);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ClientAPIMapper().convertToDTO(clientCreated), HttpStatus.CREATED);
	}

}
