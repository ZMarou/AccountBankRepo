package com.sg.bankaccount.service;

import java.util.List;

import com.sg.bankaccount.dto.HistoryDTO;
import com.sg.bankaccount.exception.BusinessException;

public interface HistoryService {

	List<HistoryDTO> showHistory (long idClient, long idAccount) throws BusinessException;

}
