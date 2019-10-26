package com.sg.bankaccount.controller.mapper;

import com.sg.bankaccount.controller.dto.AccountDTO;
import com.sg.bankaccount.domain.Account;

public class AccountAPIMapper extends AbstractAPIMapper<Account, AccountDTO>{

	@Override
	public AccountDTO convertToDTO(Account domain) {
		AccountDTO dto = new AccountDTO();
		dto.setId(domain.getId());
		dto.setAmountOverdraft(domain.getAmountOverdraft());
		dto.setBalance(domain.getBalance());
		dto.setClientId(domain.getClient().getId());
		return dto;
	}

}
