package com.sg.bankaccount.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.bankaccount.controller.dto.OperationParamDTO;
import com.sg.bankaccount.domain.api.AccountAPI;
import com.sg.bankaccount.domain.api.HistoryAPI;
import com.sg.bankaccount.domain.constants.AccountConstants;
import com.sg.bankaccount.domain.enums.OperationType;
import com.sg.bankaccount.domain.exception.BusinessException;
import com.sg.bankaccount.domain.service.AccountService;
import com.sg.bankaccount.domain.service.ClientService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class Account implements AccountAPI {
	
	private long id;
	
	private double balance;
	
	private Client client;
	
	private double amountOverdraft;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HistoryAPI historyAPI;

	public Account(long id, double balance, Client client, double amountOverdraft) {
		super();
		this.id = id;
		this.balance = balance;
		this.client = client;
		this.amountOverdraft = amountOverdraft;
	}

	public Account(double balance, Client client, double amountOverdraft) {
		super();
		this.balance = balance;
		this.client = client;
		this.amountOverdraft = amountOverdraft;
	}
	
	@Override
	public Account addAccount(long clientId) throws BusinessException {
		Client client = clientService.validateClient(clientId);
		if(client == null) {
			throw new BusinessException("Invalid client");
		}
		Account account = new Account(0, client, AccountConstants.AMOUNT_OVERDRAFT_CONSTANT);
		Account accountCreated = accountService.save(account); 
		return accountCreated;
	}
	
	 @Override
	public Account deposit(OperationParamDTO operationParamDTO) throws BusinessException {
	        Account account = accountService.validateOperation(operationParamDTO.getIdClient(), operationParamDTO.getIdAccount(),
	                operationParamDTO.getAmount());
	        account.setBalance(account.getBalance() + operationParamDTO.getAmount());
	        Account accountCreated = accountService.save(account);	     
	        
	        History history = new History(OperationType.DEPOSIT, operationParamDTO.getAmount(), accountCreated);
	        historyAPI.saveHistory(history);
	        return accountCreated;
	    }

	 @Override
	public Account withdrawal(OperationParamDTO operationParamDTO) throws BusinessException {
	        Account account = accountService.validateOperation(operationParamDTO.getIdClient(), operationParamDTO.getIdAccount(),
	                operationParamDTO.getAmount());
	        account.setBalance(account.getBalance() - operationParamDTO.getAmount());
	        
	        if (account.getBalance() < account.getAmountOverdraft()) {
	            throw new BusinessException("Insufficient resource");
	        }
	        
	        Account accountCreated = accountService.save(account);	     
	        History history = new History(OperationType.WITHDRAWAL, operationParamDTO.getAmount(), accountCreated);
	        historyAPI.saveHistory(history);
	        return accountCreated;
	    }



}
