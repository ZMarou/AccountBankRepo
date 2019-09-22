package com.sg.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.bankaccount.model.Account;

@Repository
public interface AccountRepositry extends JpaRepository<Account, Long> {

}
