package com.sg.bankaccount.service.impl;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.bankaccount.dto.AccountDTO;
import com.sg.bankaccount.dto.OperationParamDTO;
import com.sg.bankaccount.enums.OperationType;
import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.model.Account;
import com.sg.bankaccount.model.History;
import com.sg.bankaccount.model.Operation;
import com.sg.bankaccount.repository.AccountRepositry;
import com.sg.bankaccount.repository.HistoryRepository;
import com.sg.bankaccount.repository.OperationRepository;
import com.sg.bankaccount.service.OperationService;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

	@Autowired
	private AccountRepositry accountRepository;
	
	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public AccountDTO saveMoney(OperationParamDTO operationParamDTO) throws BusinessException {
		Account account = validateOperation(operationParamDTO.getIdClient(), operationParamDTO.getIdAccount(),
				operationParamDTO.getAmount());

		Operation operation = new Operation(OperationType.DEPOSIT, operationParamDTO.getAmount());
		operation = operationRepository.save(operation);
		
		History history = new History();
		history.setBalance(account.getBalance() + operationParamDTO.getAmount());
		history.setOperation(operation);
		history.setDate(new Date());
		history.setAccount(account);
		history = historyRepository.save(history);
		
		account.getHistory().add(history);
		account.setBalance(account.getBalance() + operationParamDTO.getAmount());

		account = accountRepository.save(account);
		return new AccountDTO(account.getId(), operationParamDTO.getIdClient(), account.getBalance(), account.getAmountDiscovered());
	}

	@Override
	public AccountDTO retrieveMoney(OperationParamDTO operationParamDTO) throws BusinessException {
		Account account = validateOperation(operationParamDTO.getIdClient(), operationParamDTO.getIdAccount(),
				operationParamDTO.getAmount());

		if (account.getBalance() - operationParamDTO.getAmount() < account.getAmountDiscovered()) {
			throw new BusinessException("Insufficient resource");
		}

		Operation operation = new Operation(OperationType.WITHDRAWAL, operationParamDTO.getAmount());
		operation = operationRepository.save(operation);
		
		History history = new History();
		history.setBalance(account.getBalance() - operationParamDTO.getAmount());
		history.setOperation(operation);
		history.setDate(new Date());
		history.setAccount(account);
		history = historyRepository.save(history);
		
		account.getHistory().add(history);
		account.setBalance(account.getBalance() - operationParamDTO.getAmount());

		account = accountRepository.save(account);
		return new AccountDTO(account.getId(), operationParamDTO.getIdClient(), account.getBalance(), account.getAmountDiscovered());
	}

	private Account validateOperation(long idClient, long idAccount, double amount) throws BusinessException {
		Optional<Account> optionalAccount = accountRepository.findById(idAccount);
		if (!optionalAccount.isPresent()) {
			throw new BusinessException("Account id not found");
		}
		Account account = optionalAccount.get();
		if (account.getClient() == null || account.getClient().getId() != idClient) {
			throw new BusinessException("Client id not matching to requested accout");
		}
		if (amount <= 0) {
			throw new BusinessException("The amount is incorrect");
		}
		return account;
	}

}
