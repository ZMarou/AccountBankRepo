package com.sg.bankaccount.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import com.sg.bankaccount.dto.AccountDTO;
import com.sg.bankaccount.dto.OperationParamDTO;
import com.sg.bankaccount.enums.OperationType;
import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.model.Account;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.model.History;
import com.sg.bankaccount.model.Operation;
import com.sg.bankaccount.repository.AccountRepositry;
import com.sg.bankaccount.repository.HistoryRepository;
import com.sg.bankaccount.repository.OperationRepository;
import com.sg.bankaccount.service.impl.OperationServiceImpl;

@RunWith(SpringRunner.class)
public class OperationServiceImplTest {

	@TestConfiguration
	static class OperationServiceImplContextConfiguration {

		@Bean
		public OperationService operationService() {
			return new OperationServiceImpl();
		}
	}

	@Autowired
	private OperationService operationService;

	@MockBean
	private HistoryRepository historyRepository;

	@MockBean
	private AccountRepositry accountRepository;

	@MockBean
	private OperationRepository operationRepository;

	private Account accountExpected;
	private Account accountCopyExpected;

	@Before
	public void setUp() {
		Client clientExpected = new Client();
		clientExpected.setId(1);
		clientExpected.setFirstName("firstName");
		clientExpected.setLastName("lastName");
		clientExpected.setEmail("test@gmail.com");
		accountExpected = new Account();
		accountExpected.setClient(clientExpected);
		accountExpected.setHistory(new ArrayList<>());
		accountExpected.setBalance(100);
		accountExpected.setAmountDiscovered(-200);
		accountCopyExpected = new Account();
		accountCopyExpected.setClient(clientExpected);
		accountCopyExpected.setHistory(new ArrayList<>());
		accountCopyExpected.setBalance(100);
		accountCopyExpected.setAmountDiscovered(-200);
	}

	@Test(expected = BusinessException.class)
	public void test_save_money_for_nonexisting_account_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());
		// When
		operationService.saveMoney(new OperationParamDTO(1l, 1l, 1000.59));
	}

	@Test(expected = BusinessException.class)
	public void test_save_money_for_illegal_clienid_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		// When
		operationService.saveMoney(new OperationParamDTO(2l, 1l, 1000.59));
	}

	@Test(expected = BusinessException.class)
	public void test_save_money_for_illegal_amount_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		// When
		operationService.saveMoney(new OperationParamDTO(2l, 1l, -1000.59));
	}

	@Test
	public void test_save_money_then_return_success() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		Operation operationExpected = new  Operation(1l, OperationType.DEPOSIT, 1000.59);
		Mockito.when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(operationExpected);
		History historyExpected = new History(1l, operationExpected, new Date(), 1100.59);
		Mockito.when(historyRepository.save(Mockito.any(History.class))).thenReturn(historyExpected);
		accountCopyExpected.setBalance(1100.59);
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(accountCopyExpected);
		// When
		AccountDTO accountActual = operationService.saveMoney(new OperationParamDTO(1l, 1l, 1000.59));
		// Then
		assertNotNull(accountActual);
		assertTrue(accountActual.getBalance() == 1100.59);

	}
	
	
	@Test(expected = BusinessException.class)
	public void test_retrieve_money_for_nonexisting_account_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());
		// When
		operationService.retrieveMoney(new OperationParamDTO(1l, 1l, 1000.59));
	}

	@Test(expected = BusinessException.class)
	public void test_retrive_money_for_illegal_clienid_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		// When
		operationService.retrieveMoney(new OperationParamDTO(2l, 1l, 1000.59));
	}

	@Test(expected = BusinessException.class)
	public void test_retrieve_money_for_illegal_amount_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		// When
		operationService.retrieveMoney(new OperationParamDTO(2l, 1l, -1000.59));
	}
	
	@Test(expected = BusinessException.class)
	public void test_retrieve_money_with_insufficient_resource_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		// When
		operationService.retrieveMoney(new OperationParamDTO(2l, 1l, 1000.59));
	}

	@Test
	public void test_retrieve_money_then_return_success() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		Operation operationExpected = new  Operation(1l, OperationType.WITHDRAWAL, 50);
		Mockito.when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(operationExpected);
		History historyExpected = new History(1l, operationExpected, new Date(), 50);
		Mockito.when(historyRepository.save(Mockito.any(History.class))).thenReturn(historyExpected);
		accountCopyExpected.setBalance(50);
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(accountCopyExpected);
		// When
		AccountDTO accountActual = operationService.retrieveMoney(new OperationParamDTO(1l, 1l, 50));
		// Then
		assertNotNull(accountActual);
		assertTrue(accountActual.getBalance() == 50);

	}

}
