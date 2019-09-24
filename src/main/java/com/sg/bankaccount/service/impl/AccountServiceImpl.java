package com.sg.bankaccount.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.model.Account;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.repository.AccountRepositry;
import com.sg.bankaccount.repository.ClientRepository;
import com.sg.bankaccount.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private ClientRepository clientRepository; 
	
	@Autowired
	private AccountRepositry accountRepository;

	@Override
	public long create(long idClient) throws BusinessException {
		Optional<Client> client = clientRepository.findById(idClient);
		if(!client.isPresent()) {
			throw new BusinessException("Client id not found");
		}
		Account account = new Account();
		account.setClient(client.get());
		account.setHistory(new ArrayList<>());
		account.setBalance(0);
		account.setAmountDiscovered(-200);
		Account accountCreated = accountRepository.save(account);
		return accountCreated.getId();
	}

}
