package com.sg.bankaccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.bankaccount.controller.mapper.HistoryAPIMapper;
import com.sg.bankaccount.domain.History;
import com.sg.bankaccount.domain.api.HistoryAPI;
import com.sg.bankaccount.domain.exception.BusinessException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/history")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {

	@Autowired
	private HistoryAPI historyAPI;

	@ApiOperation(value = "Show history transactions")
	@GetMapping(path = "/{clientId}/{accountId}")
	public ResponseEntity<Object> showHistory(@PathVariable("clientId") long clientId,
			@PathVariable("accountId") long accountId) {
		List<History> result;
		try {
			result = historyAPI.showHistory(clientId, accountId);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new HistoryAPIMapper().convertToDTOs(result), HttpStatus.OK);
	}

}
