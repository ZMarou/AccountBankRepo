package com.sg.bankaccount.domain.service;

import com.sg.bankaccount.domain.Account;
import com.sg.bankaccount.domain.exception.BusinessException;

public interface AccountService {

	Account save(Account account);

	Account validateOperation(long idClient, long idAccount, double amount) throws BusinessException;

}
