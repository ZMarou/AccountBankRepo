package com.sg.bankaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.bankaccount.controller.dto.OperationParamDTO;
import com.sg.bankaccount.controller.mapper.AccountAPIMapper;
import com.sg.bankaccount.domain.Account;
import com.sg.bankaccount.domain.api.AccountAPI;
import com.sg.bankaccount.domain.exception.BusinessException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	@Autowired
	private AccountAPI accountAPI;

	@ApiOperation(value = "Create new account to client")
	@PostMapping
	public ResponseEntity<Object> createAccount(@RequestBody long clientId) {
		Account account;
		try {
			account = accountAPI.addAccount(clientId);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new AccountAPIMapper().convertToDTO(account), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Save money in an account")
	@ApiResponses(value = {
			@ApiResponse(code = 409, message = "Conflict detected while trying to update account")
	})
	@PostMapping(path = "/deposit")
	public ResponseEntity<Object> deposit(@RequestBody OperationParamDTO operationParamDTO) {
		Account account;
		try {
			account = accountAPI.deposit(operationParamDTO);
		} catch( ObjectOptimisticLockingFailureException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new AccountAPIMapper().convertToDTO(account), HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieve money in an account")
	@ApiResponses(value = {
			@ApiResponse(code = 409, message = "Conflict detected while trying to update account")
	})	@PostMapping(path = "/withdrawal")
	public ResponseEntity<Object> withdrawal(@RequestBody OperationParamDTO operationParamDTO) {
		Account account;
		try {
			account = accountAPI.withdrawal(operationParamDTO);
		} catch( ObjectOptimisticLockingFailureException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new AccountAPIMapper().convertToDTO(account), HttpStatus.OK);
	}

}
