package com.sg.bankaccount.domain.api;

import com.sg.bankaccount.controller.dto.ClientDTO;
import com.sg.bankaccount.domain.Client;
import com.sg.bankaccount.domain.exception.BusinessException;

public interface ClientAPI {

	Client addClient(ClientDTO client) throws BusinessException;

}