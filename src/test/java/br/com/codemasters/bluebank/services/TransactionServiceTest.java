package br.com.codemasters.bluebank.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.codemasters.bluebank.domain.dtos.DepositDTO;
import br.com.codemasters.bluebank.domain.dtos.DraftDTO;
import br.com.codemasters.bluebank.domain.dtos.TransferDTO;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.entities.TransactionEntity;
import br.com.codemasters.bluebank.domain.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
	
	TransactionService transactionService;
	
	@Mock
	TransactionRepository transactionRepository;
	
	@Mock
	AccountService accountService;
	
	@Mock
	Pageable pageable;
		
	
	@BeforeEach
	void setup(){
		this.transactionService = new TransactionService(transactionRepository, accountService);
	}

	@Test
	void deposit() {
		when(accountService.findByAccount(anyString())).thenReturn(accountEntity());
		assertDoesNotThrow( () -> transactionService.deposit(depositDTO()));
	}
	
	@Test
	void draft() {
		when(accountService.findByAccount(anyString())).thenReturn(accountEntity());
		assertDoesNotThrow( () -> transactionService.draft(draftDTO()));
	}
	
	@Test
	void trasfer() {
		when(accountService.findByAccount(anyString())).thenReturn(accountEntity());
		assertDoesNotThrow( () -> transactionService.transfer(transferDTO()));
	}
	
	@Test
	void getAllTransaction() {
		when(transactionRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(listTransactionEntity()));
		assertDoesNotThrow( () -> transactionService.getAllTransaction(pageable));
	}
	
	@Test
	void getByAccountNumber() {	
		when(transactionRepository.findByAccountNumber(anyString())).thenReturn(listTransactionEntity());
		assertDoesNotThrow( () -> transactionService.getByAccountNumber("15"));
	}
	
	List<TransactionEntity> listTransactionEntity(){
		return Collections.singletonList(buildTransactionEntity());
	}
	
	DepositDTO depositDTO() {
		return DepositDTO.builder().accounNumber("30").value(50D).build();
	}
	
	DraftDTO draftDTO() {
		return DraftDTO.builder().accountNumber("15").value(150D).build();
	}
	
	TransferDTO transferDTO() {
		return TransferDTO.builder().accountIdDestiny("500").accountIdOrigin("100").obs("ok").value(40D).build();
	}
		
	AccountEntity accountEntity() {
		return AccountEntity.builder().balance(500D).build();
	}
	
	TransactionEntity buildTransactionEntity() {
		return TransactionEntity.builder().account(accountEntity()).build();
	}

}
