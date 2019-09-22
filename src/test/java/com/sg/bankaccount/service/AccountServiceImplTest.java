package com.sg.bankaccount.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.model.Account;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.repository.AccountRepositry;
import com.sg.bankaccount.repository.ClientRepository;
import com.sg.bankaccount.service.impl.AccountServiceImpl;

@RunWith(SpringRunner.class)
public class AccountServiceImplTest {

	@TestConfiguration
	static class AccountServiceImplTestContextConfiguration {

		@Bean
		public AccountService accountService() {
			return new AccountServiceImpl();
		}
	}

	@Autowired
	private AccountService accountService;
	
	@MockBean
	private ClientRepository clientRepository;
	
	@MockBean
	private AccountRepositry accountRepository;
	
	@Before
	public void setUp() {
	}
	
	@Test(expected=BusinessException.class)
	public void test_add_account_to_nonexistent_client_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.empty());
		// when
		accountService.create(1L);
	}

	@Test
	public void test_add_account_to_client_then_return_accountid() throws BusinessException {
		// Given
		Client clientExpected = new Client();
		clientExpected.setId(1);
		clientExpected.setFirstName("firstName");
		clientExpected.setLastName("lastName");
		clientExpected.setEmail("test@gmail.com");
		Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(clientExpected));
		
		Account accountExpected = new Account();
		accountExpected.setId(100);
		accountExpected.setClient(clientExpected);
		accountExpected.setHistory(new ArrayList<>());
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(accountExpected);

		// when
		long accountId = accountService.create(1L);
		// then
		assertTrue(accountId == 100);
	}
}
