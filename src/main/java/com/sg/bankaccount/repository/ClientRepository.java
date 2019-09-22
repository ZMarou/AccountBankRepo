package com.sg.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.bankaccount.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
