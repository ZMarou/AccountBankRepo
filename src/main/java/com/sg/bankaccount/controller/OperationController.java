package com.sg.bankaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/api/aperation")
public class OperationController {

	@Autowired
	private OperationService operationService;

	@ApiOperation(value = "Save money in an account")
	@PostMapping(path = "/deposit")
	public ResponseEntity<Object> deposit(@RequestBody OperationParamDTO operationParamDTO) {
		AccountDTO result;
		try {
			result = operationService.saveMoney(operationParamDTO);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieve money in an account")
	@PostMapping(path = "/withdrawal")
	public ResponseEntity<Object> withdrawal(@RequestBody OperationParamDTO operationParamDTO) {
		AccountDTO result;
		try {
			result = operationService.retrieveMoney(operationParamDTO);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
