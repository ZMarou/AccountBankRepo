package com.sg.bankaccount.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.bankaccount.dto.HistoryDTO;
import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.model.Account;
import com.sg.bankaccount.model.History;
import com.sg.bankaccount.model.Operation;
import com.sg.bankaccount.repository.AccountRepositry;
import com.sg.bankaccount.repository.HistoryRepository;
import com.sg.bankaccount.service.HistoryService;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private AccountRepositry accountRepository;

	@Override
	public List<HistoryDTO> showHistory(long idClient, long idAccount) throws BusinessException {
		Optional<Account> optionalAccount = accountRepository.findById(idAccount);
		if (!optionalAccount.isPresent()) {
			throw new BusinessException("Account id not found");
		}
		Account account = optionalAccount.get();
		if (account.getClient() == null || account.getClient().getId() != idClient) {
			throw new BusinessException("Client id not matching to requested accout");
		}
		List<HistoryDTO> result = new ArrayList<>();
		List<History> histories = historyRepository.findByAccountId(idAccount);
		result = histories.stream().map(x -> new HistoryDTO(x.getId(), ((Operation) x.getOperation()).getOperationType().toString(), x.getDate(),
				((Operation) x.getOperation()).getAmount(), x.getBalance())).collect(Collectors.toList());
		return result;
	}

}
