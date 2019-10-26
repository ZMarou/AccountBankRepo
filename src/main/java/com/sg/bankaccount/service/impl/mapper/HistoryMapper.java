package com.sg.bankaccount.service.impl.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.bankaccount.domain.History;
import com.sg.bankaccount.infra.entities.HistoryEntity;
import com.sg.bankaccount.infra.repository.HistoryRepository;

@Component
public final class HistoryMapper extends AbstractMapper<HistoryEntity, History> {
	
	@Autowired
	private HistoryRepository historyRepository; 
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public HistoryEntity convertToEntity(History domain) {
		if (domain == null)
			return null;
		HistoryEntity entity = new HistoryEntity();
		if(domain.getId() > 0) {			
			entity = historyRepository.findById(domain.getId()).orElse(new HistoryEntity());
		}
		entity.setId(domain.getId());
		entity.setAccount(accountMapper.convertToEntity(domain.getAccount()));
		entity.setAmount(domain.getAmount());
		entity.setDate(domain.getDate());
		entity.setOperation(domain.getOperation());
		return entity;
	}

	@Override
	public History convertToDomain(HistoryEntity entity) {
		if (entity == null)
			return null;
		History domain = new History();
		domain.setId(entity.getId());
		domain.setAccount(accountMapper.convertToDomain(entity.getAccount()));
		domain.setAmount(entity.getAmount());
		domain.setDate(entity.getDate());
		domain.setOperation(entity.getOperation());
		return domain;
	}

}
