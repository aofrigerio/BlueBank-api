package br.com.codemasters.bluebank.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
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
	
	DepositDTO depositDTO() {
		return DepositDTO.builder().accounNumber("30").value(50D).build();
	}
	
	private DraftDTO draftDTO() {
		return DraftDTO.builder().accountNumber("15").value(150D).build();
	}
	
	private TransferDTO transferDTO() {
		return TransferDTO.builder().accountIdDestiny("500").accountIdOrigin("100").obs("ok").value(40D).build();
	}
		
	AccountEntity accountEntity() {
		return AccountEntity.builder().balance(500D).build();
	}

}
