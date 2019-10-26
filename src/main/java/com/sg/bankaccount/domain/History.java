package com.sg.bankaccount.domain;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.bankaccount.domain.api.HistoryAPI;
import com.sg.bankaccount.domain.enums.OperationType;
import com.sg.bankaccount.domain.exception.BusinessException;
import com.sg.bankaccount.domain.service.HistoryService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class History implements HistoryAPI {
	
	private long id;

	private OperationType operation;

	private Date date;
	
	private double amount;
	
	private Account account;
	
	@Autowired
	private HistoryService historyService;

	public History(OperationType operation, double amount, Account account) {
		super();
		this.operation = operation;
		this.amount = amount;
		this.account = account;
	}
	
	@Override
	public History saveHistory(History history) throws BusinessException {
		historyService.isValidated(history);
		history.setDate(new Date());
		History historyCreated = historyService.save(history);
		return historyCreated;
	}
	
	@Override
	public List<History> showHistory(long clientId, long accountId) throws BusinessException{
		historyService.validateClientAccount(clientId, accountId);
		List<History> histories = historyService.getHistories(accountId);
		return histories;
	}

}
