package com.sg.bankaccount.domain.service;

import com.sg.bankaccount.domain.Client;

public interface ClientService {

	Client save(Client client);

	Client validateClient(long clientId);

}
