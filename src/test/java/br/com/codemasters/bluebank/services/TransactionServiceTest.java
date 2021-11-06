package br.com.codemasters.bluebank.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.codemasters.bluebank.domain.dtos.DepositDTO;
import br.com.codemasters.bluebank.domain.dtos.DraftDTO;
import br.com.codemasters.bluebank.domain.dtos.TransferDTO;
import br.com.codemasters.bluebank.domain.entities.TransactionEntity;
import br.com.codemasters.bluebank.domain.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
	
	TransactionService transactionService;
	
	@Mock
	TransactionRepository transactionRepository;
	
	@BeforeEach
	void setup(){
		this.transactionService = new TransactionService(transactionRepository);
	}

	@Test
	void deposit() {
		when(transactionRepository.save(any())).thenReturn(entityTransaction());
		assertDoesNotThrow( () -> transactionService.deposit(any(DepositDTO.class)));
	}
	
	@Test
	void draft() {
		when(transactionRepository.save(any())).thenReturn(entityTransaction());
		assertDoesNotThrow( () -> transactionService.deposit(any(DepositDTO.class)));
	}
	
	@Test
	void draftEmpty() {
		when(transactionRepository.save(any())).thenReturn(entityTransaction());
		assertDoesNotThrow( () -> transactionService.draft(any(DraftDTO.class)));
	}
	
	@Test
	void draftWithoutFunds() {
		when(transactionRepository.save(any())).thenReturn(entityTransaction());
		assertDoesNotThrow( () -> transactionService.draft(any(DraftDTO.class)));
	}
	
	@Test
	void transferWithBalance() {
		when(transactionRepository.save(any())).thenReturn(entityTransaction());
		assertDoesNotThrow( () -> transactionService.transfer(any(TransferDTO.class)));
	}
	
	@Test
	void transferWithoutBalanceInOriginAccount() {
		
	}
	
	TransactionEntity entityTransaction() {
		return TransactionEntity.builder().account(null).value(5000D).build();
	}

}
