package com.sg.bankaccount.service.impl.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.bankaccount.domain.Account;
import com.sg.bankaccount.infra.entities.AccountEntity;
import com.sg.bankaccount.infra.repository.AccountRepository;

@Component
public final class AccountMapper extends AbstractMapper<AccountEntity, Account> {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClientMapper clientMapper;

	@Override
	public AccountEntity convertToEntity(Account domain) {
		if (domain == null)
			return null;
		AccountEntity entity = new AccountEntity();
		if(domain.getId() > 0) {			
			entity = accountRepository.findById(domain.getId()).orElse(new AccountEntity());
		}
		entity.setBalance(domain.getBalance());
		entity.setAmountOverdraft(domain.getAmountOverdraft());
		entity.setClient(clientMapper.convertToEntity(domain.getClient()));
		return entity;
	}

	@Override
	public Account convertToDomain(AccountEntity entity) {
		if (entity == null)
			return null;
		Account domain = new Account();
		domain.setId(entity.getId());
		domain.setBalance(entity.getBalance());
		domain.setAmountOverdraft(entity.getAmountOverdraft());
		domain.setClient(clientMapper.convertToDomain(entity.getClient()));
		return domain;
	}

}
