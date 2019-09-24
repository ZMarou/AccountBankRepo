package com.sg.bankaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.bankaccount.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

	List<History> findByAccountId(long idAccount);
}
