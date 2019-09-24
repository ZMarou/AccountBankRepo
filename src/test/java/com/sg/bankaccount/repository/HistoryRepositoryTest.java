package com.sg.bankaccount.repository;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.sg.bankaccount.enums.OperationType;
import com.sg.bankaccount.model.Account;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.model.History;
import com.sg.bankaccount.model.Operation;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HistoryRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private HistoryRepository historyRepository;
    
    @Test
    public void test_find_history_by_accountid_then_return_list() {
        // given
        Operation op1 = new Operation(OperationType.DEPOSIT, 500);
        Operation op2 = new Operation(OperationType.WITHDRAWAL, 300);
        Operation op3 = new Operation(OperationType.DEPOSIT, 790);
        op1 = entityManager.persist(op1);
        op2 = entityManager.persist(op2);
        op3 = entityManager.persist(op3);
        
        Client client = new Client();
		client.setFirstName("firstName");
		client.setLastName("lastName");
		client.setEmail("test@gmail.com");
		client = entityManager.persist(client);
		
		entityManager.flush();

		Account account = new Account();
		account.setClient(client);
		account.setBalance(0);
		account.setHistory(new ArrayList<>());
		account = entityManager.persist(account);
		
		entityManager.flush();
		
		History h1 = new History();
		h1.setAccount(account);
		h1.setBalance(500);
		h1.setDate(new Date());
		h1.setOperation(op1);
		History h2 = new History();
		h2.setAccount(account);
		h2.setBalance(200);
		h2.setDate(new Date());
		h2.setOperation(op2);
		History h3 = new History();
		h3.setAccount(account);
		h3.setBalance(990);
		h3.setDate(new Date());
		h3.setOperation(op3);
		entityManager.persist(h1);
		entityManager.persist(h2);
		entityManager.persist(h3);
		
		entityManager.flush();
		
        // when
        List<History> result = historyRepository.findByAccountId(account.getId());
     
        // then
        assertTrue(!CollectionUtils.isEmpty(result) && result.size() == 3);
    }
    
    @Test
    public void test_find_history_by_accountid_when_noHistroy_onaccount_then_return_empty_list() {
        // given
        // when
        List<History> result = historyRepository.findByAccountId(1);
     
        // then
        assertTrue(result != null);
    }

}
