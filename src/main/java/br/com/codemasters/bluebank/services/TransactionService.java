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
import br.com.codemasters.bluebank.domain.enuns.TypeTransactionEnum;
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
		AccountEntity accountEntity = accountService.findByAccount(depositDTO.getAccounNumber());		
		TransactionEntity entity = TransactionEntity.builder().account(accountEntity).value(depositDTO.getValue()).obs(depositDTO.getObs()).typeTransaction(TypeTransactionEnum.DEPOSIT).build();			
		accountService.depositUpdate(accountEntity.getId(), depositDTO.getValue());
		transactionRepository.save(entity);
	}
	
	@Transactional
	public void draft(DraftDTO draftDto) {			
		AccountEntity accountEntity = accountService.findByAccount(draftDto.getAccountNumber());						
		accountService.draftUpdate(accountEntity.getId(), draftDto.getValue());		
		TransactionEntity entity = TransactionEntity.builder().account(accountEntity).value(draftDto.getValue()).obs(draftDto.getObs()).typeTransaction(TypeTransactionEnum.DRAFT).build();
		transactionRepository.save(entity);
	}
	
	@Transactional
	public void transfer(TransferDTO transferDTO){		
		AccountEntity accountOrigin = accountService.findByAccount(transferDTO.getAccountIdOrigin());		
		AccountEntity accountDestiny = accountService.findByAccount(transferDTO.getAccountIdDestiny());
		accountService.draftUpdate(accountOrigin.getId(), transferDTO.getValue());
		accountService.depositUpdate(accountDestiny.getId(), transferDTO.getValue());
		TransactionEntity entityOrigin = TransactionEntity.builder().account(accountOrigin).value(transferDTO.getValue()).obs(transferDTO.getObs()).typeTransaction(TypeTransactionEnum.TRANSFER).build();
		TransactionEntity entityDestiny = TransactionEntity.builder().account(accountDestiny).value(transferDTO.getValue()).obs(transferDTO.getObs()).typeTransaction(TypeTransactionEnum.TRANSFER).build();
		transactionRepository.saveAll(List.of(entityOrigin,entityDestiny));	
	}
	
}
