package com.sg.bankaccount.domain.api;

import java.util.List;

import com.sg.bankaccount.domain.History;
import com.sg.bankaccount.domain.exception.BusinessException;

public interface HistoryAPI {

	List<History> showHistory(long clientId, long accountId) throws BusinessException;

	History saveHistory(History history) throws BusinessException;

}