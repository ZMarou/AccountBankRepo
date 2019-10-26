package com.sg.bankaccount.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sg.bankaccount.domain.History;
import com.sg.bankaccount.domain.exception.BusinessException;
import com.sg.bankaccount.domain.service.HistoryService;
import com.sg.bankaccount.infra.entities.AccountEntity;
import com.sg.bankaccount.infra.entities.HistoryEntity;
import com.sg.bankaccount.infra.repository.AccountRepository;
import com.sg.bankaccount.infra.repository.HistoryRepository;
import com.sg.bankaccount.service.impl.mapper.HistoryMapper;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private HistoryMapper historyMapper;

	@Override
	public History isValidated(History history) throws BusinessException {
		if (history.getAmount() > 0 && history.getAccount() != null) {
			return history;
		} else {
			throw new BusinessException("Invalid data history");
		}
	}

	@Override
	public History save(History history) {
		HistoryEntity historyEntity = historyMapper.convertToEntity(history);
		HistoryEntity historyCreated = historyRepository.save(historyEntity);
		return historyMapper.convertToDomain(historyCreated);
	}

	@Override
	public void validateClientAccount(long clientId, long accountId) throws BusinessException {
		Optional<AccountEntity> optionalAccount = accountRepository.findById(accountId);
		final AccountEntity account = optionalAccount.orElseThrow(() -> new BusinessException("Account id not found"));
		if (account.getClient() == null || account.getClient().getId() != clientId) {
			throw new BusinessException("Client id not matching to requested accout");
		}
	}

	@Override
	public List<History> getHistories(long accountId) {
		List<HistoryEntity> HistoryEntities = historyRepository.findByAccountId(accountId);
		List<History> histories = new ArrayList<History>();
		if(!CollectionUtils.isEmpty(HistoryEntities)) {
			for (HistoryEntity entity : HistoryEntities) {			
				histories.add(historyMapper.convertToDomain(entity));
			}
		}
		return histories;
	}

}
