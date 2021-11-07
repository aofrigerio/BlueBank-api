package br.com.codemasters.bluebank.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codemasters.bluebank.domain.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	
	List<TransactionEntity> findByAccountNumber(String accountNumber);

}
