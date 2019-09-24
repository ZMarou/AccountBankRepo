package com.sg.bankaccount.service;

import com.sg.bankaccount.dto.AccountDTO;
import com.sg.bankaccount.dto.OperationParamDTO;
import com.sg.bankaccount.exception.BusinessException;

public interface OperationService {

	AccountDTO saveMoney(OperationParamDTO operationParamDTO) throws BusinessException;

	AccountDTO retrieveMoney(OperationParamDTO operationParamDTO) throws BusinessException;

}
