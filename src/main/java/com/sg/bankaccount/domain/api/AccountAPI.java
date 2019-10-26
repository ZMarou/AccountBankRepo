package com.sg.bankaccount.domain.api;

import com.sg.bankaccount.controller.dto.OperationParamDTO;
import com.sg.bankaccount.domain.Account;
import com.sg.bankaccount.domain.exception.BusinessException;

public interface AccountAPI {

	Account addAccount(long clientId) throws BusinessException;

	Account deposit(OperationParamDTO operationParamDTO) throws BusinessException;

	Account withdrawal(OperationParamDTO operationParamDTO) throws BusinessException;

}