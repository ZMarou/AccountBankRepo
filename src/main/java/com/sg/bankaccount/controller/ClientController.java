package com.sg.bankaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.bankaccount.dto.ClientDTO;
import com.sg.bankaccount.service.ClientService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@ApiOperation(value = "Create client")
	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO dto) {
		ClientDTO clientCreated = clientService.create(dto);
		return new ResponseEntity<>(clientCreated, HttpStatus.CREATED);
	}

}
