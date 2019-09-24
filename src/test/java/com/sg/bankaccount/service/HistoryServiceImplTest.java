package com.sg.bankaccount.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
import org.springframework.util.CollectionUtils;

import com.sg.bankaccount.dto.HistoryDTO;
import com.sg.bankaccount.enums.OperationType;
import com.sg.bankaccount.exception.BusinessException;
import com.sg.bankaccount.model.Account;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.model.History;
import com.sg.bankaccount.model.Operation;
import com.sg.bankaccount.repository.AccountRepositry;
import com.sg.bankaccount.repository.HistoryRepository;
import com.sg.bankaccount.service.impl.HistoryServiceImpl;

@RunWith(SpringRunner.class)
public class HistoryServiceImplTest {

	@TestConfiguration
	static class HistoryServiceImplContextConfiguration {

		@Bean
		public HistoryService historyService() {
			return new HistoryServiceImpl();
		}
	}

	@Autowired
	private HistoryService historyService;

	@MockBean
	private HistoryRepository historyRepository;

	@MockBean
	private AccountRepositry accountRepository;
	
	private Account accountExpected;

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
	}

	@Test(expected = BusinessException.class)
	public void test_show_history_for_nonexisting_account_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());
		// When
		historyService.showHistory(1l, 1l);
	}

	@Test(expected = BusinessException.class)
	public void test_show_history_for_illegal_clientid_then_throw_exception() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		// When
		historyService.showHistory(2l, 1l);
	}

	@Test
	public void test_show_history_then_return_list() throws BusinessException {
		// Given
		Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountExpected));
		Operation op1 = new Operation(1, OperationType.DEPOSIT, 500);
        Operation op2 = new Operation(2, OperationType.WITHDRAWAL, 300);
        Operation op3 = new Operation(3, OperationType.DEPOSIT, 790);
        
        History h1 = new History();
		h1.setAccount(accountExpected);
		h1.setBalance(500);
		h1.setDate(new Date());
		h1.setOperation(op1);
		History h2 = new History();
		h2.setAccount(accountExpected);
		h2.setBalance(200);
		h2.setDate(new Date());
		h2.setOperation(op2);
		History h3 = new History();
		h3.setAccount(accountExpected);
		h3.setBalance(990);
		h3.setDate(new Date());
		h3.setOperation(op3);
		
		List<History> data = Arrays.asList(h1, h2, h3);
		Mockito.when(historyRepository.findByAccountId(1)).thenReturn(data);
		
		// When
		List<HistoryDTO> historyActuals = historyService.showHistory(1l, 1l);
		
		//then
		assertTrue(!CollectionUtils.isEmpty(historyActuals) && historyActuals.size() == 3);
	}

}
