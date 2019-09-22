package com.sg.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.bankaccount.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
