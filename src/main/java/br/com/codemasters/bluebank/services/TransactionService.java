package br.com.codemasters.bluebank.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codemasters.bluebank.domain.dtos.DepositDTO;
import br.com.codemasters.bluebank.domain.dtos.DraftDTO;
import br.com.codemasters.bluebank.domain.dtos.TransferDTO;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.entities.TransactionEntity;
import br.com.codemasters.bluebank.domain.repository.TransactionRepository;

@Service
public class TransactionService {
		
	private final TransactionRepository transactionRepository;	
	private final AccountService accountService;
	
	@Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService ){
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }
	
	@Transactional
	public void deposit(DepositDTO depositDTO){
		AccountEntity accountEntity = accountService.findEntityById(depositDTO.getAccountId());		
		TransactionEntity entity = TransactionEntity.builder().account(accountEntity).value(depositDTO.getValue()).build();			
		accountService.depositUpdate(accountEntity.getId(), depositDTO.getValue());
		transactionRepository.save(entity);
	}
	
	@Transactional
	public void draft(DraftDTO draftDto) {			
		AccountEntity accountEntity = accountService.findEntityById(draftDto.getAccountId());						
		accountService.draftUpdate(accountEntity.getId(), draftDto.getValue());		
		TransactionEntity entity = TransactionEntity.builder().account(accountEntity).value(draftDto.getValue()).build();
		transactionRepository.save(entity);
	}
	
	@Transactional
	public void transfer(TransferDTO transferDTO){		
		AccountEntity accountOrigin = accountService.findEntityById(transferDTO.getAccountIdOrigin());		
		AccountEntity accountDestiny = accountService.findEntityById(transferDTO.getAccountIdDestiny());
		accountService.draftUpdate(accountOrigin.getId(), transferDTO.getValue());
		accountService.depositUpdate(accountDestiny.getId(), transferDTO.getValue());
		TransactionEntity entityOrigin = TransactionEntity.builder().account(accountOrigin).value(transferDTO.getValue()).build();
		TransactionEntity entityDestiny = TransactionEntity.builder().account(accountDestiny).value(transferDTO.getValue()).build();
		transactionRepository.saveAll(List.of(entityOrigin,entityDestiny));	
	}
	
}
