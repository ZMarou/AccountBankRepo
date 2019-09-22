package com.sg.bankaccount.service;

import com.sg.bankaccount.exception.BusinessException;

public interface AccountService {

	long create(long idClient) throws BusinessException;

}
