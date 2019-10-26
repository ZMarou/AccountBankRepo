package com.sg.bankaccount.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.bankaccount.domain.Account;
import com.sg.bankaccount.domain.exception.BusinessException;
import com.sg.bankaccount.domain.service.AccountService;
import com.sg.bankaccount.infra.entities.AccountEntity;
import com.sg.bankaccount.infra.repository.AccountRepository;
import com.sg.bankaccount.service.impl.mapper.AccountMapper;

@Service
public class AccountServcieImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Account save(Account account) {
		AccountEntity accountEntity = accountMapper.convertToEntity(account);
		AccountEntity accountCreated = accountRepository.save(accountEntity);
		return accountMapper.convertToDomain(accountCreated);
	}

	@Override
	public Account validateOperation(long idClient, long idAccount, double amount) throws BusinessException {
		Optional<AccountEntity> optionalAccount = accountRepository.findById(idAccount);

        final AccountEntity account = optionalAccount.orElseThrow(() -> new BusinessException("Account id not found"));

        if (account.getClient() == null || account.getClient().getId() != idClient) {
            throw new BusinessException("Client id not matching to requested account");
        }
        if (amount <= 0) {
            throw new BusinessException("The amount is incorrect");
        }
        return accountMapper.convertToDomain(account);
	}

}
