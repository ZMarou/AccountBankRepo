package com.sg.bankaccount.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.bankaccount.dto.AccountDTO;
import com.sg.bankaccount.dto.OperationParamDTO;
import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.service.OperationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/operation")
public class OperationController {

	@Autowired
	private OperationService operationService;

	@ApiOperation(value = "Save money in an account")
	@ApiResponses(value = {
			@ApiResponse(code = 409, message = "Conflict detected while trying to update account")
	})
	@PostMapping(path = "/deposit")
	public ResponseEntity<Object> deposit(@RequestBody OperationParamDTO operationParamDTO) {
		AccountDTO result;
		try {
			result = operationService.saveMoney(operationParamDTO);
		} catch( ObjectOptimisticLockingFailureException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieve money in an account")
	@ApiResponses(value = {
			@ApiResponse(code = 409, message = "Conflict detected while trying to update account")
	})	@PostMapping(path = "/withdrawal")
	public ResponseEntity<Object> withdrawal(@RequestBody OperationParamDTO operationParamDTO) {
		AccountDTO result;
		try {
			result = operationService.retrieveMoney(operationParamDTO);
		} catch( ObjectOptimisticLockingFailureException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
