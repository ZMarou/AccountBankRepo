package com.sg.bankaccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.bankaccount.dto.HistoryDTO;
import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.service.HistoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/history")
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@ApiOperation(value = "Show history transactions")
	@GetMapping(path = "/{clientId}/{accountId}")
	public ResponseEntity<Object> showHistory(@PathVariable("clientId") long clientId,
			@PathVariable("accountId") long accountId) {
		List<HistoryDTO> result;
		try {
			result = historyService.showHistory(clientId, accountId);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
