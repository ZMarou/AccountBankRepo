package com.sg.bankaccount.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.bankaccount.dto.ClientDTO;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.repository.ClientRepository;
import com.sg.bankaccount.service.impl.ClientServiceImpl;

@RunWith(SpringRunner.class)
public class ClientServiceImplTest {

	@TestConfiguration
	static class ClientServiceImplTestContextConfiguration {

		@Bean
		public ClientService employeeService() {
			return new ClientServiceImpl();
		}
	}

	@Autowired
	private ClientService clientService;

	@MockBean
	private ClientRepository clientRepository;
	
	@Before
	public void setUp() {
	}

	@Test
	public void test_create_client_then_return_clientcreated() {
		// Given
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setFirstName("firstName");
		clientDTO.setLastName("lastName");
		clientDTO.setEmail("test@gmail.com");

		Client clientExpected = new Client();
		clientExpected.setId(1);
		clientExpected.setFirstName("firstName");
		clientExpected.setLastName("lastName");
		clientExpected.setEmail("test@gmail.com");
		Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientExpected);

		// when
		ClientDTO clientActual = clientService.create(clientDTO);
		// then
		assertNotNull(clientActual);
		assertTrue(clientActual.getId() == 1);
	}
}
