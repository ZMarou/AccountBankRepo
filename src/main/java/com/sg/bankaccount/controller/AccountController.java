package com.sg.bankaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.service.AccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@ApiOperation(value = "Create new account to client")
	@PostMapping
	public ResponseEntity<Long> createClient(@RequestBody Long clientId) {
		Long accountId;
		try {
			accountId = accountService.create(clientId);
		} catch (BusinessException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(accountId, HttpStatus.CREATED);
	}

}
