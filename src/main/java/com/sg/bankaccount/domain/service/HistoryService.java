package com.sg.bankaccount.domain.service;

import java.util.List;

import com.sg.bankaccount.domain.History;
import com.sg.bankaccount.domain.exception.BusinessException;

public interface HistoryService {

	History isValidated(History history) throws BusinessException;

	History save(History history);

	void validateClientAccount(long clientId, long accountId) throws BusinessException;

	List<History> getHistories(long accountId);

}
