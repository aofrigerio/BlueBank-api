package br.com.codemasters.bluebank.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.codemasters.bluebank.domain.dtos.DepositDTO;
import br.com.codemasters.bluebank.domain.dtos.DraftDTO;
import br.com.codemasters.bluebank.domain.dtos.TransferDTO;
import br.com.codemasters.bluebank.domain.entities.TransactionEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import br.com.codemasters.bluebank.domain.repository.TransactionRepository;

@Service
public class TransactionService {
		
	private final TransactionRepository transactionRepository;
	
	@Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
	
	@Transactional
	public void deposit(DepositDTO depositDTO){
		TransactionEntity entity = TransactionEntity.builder().account(null).value(depositDTO.getValue()).build();
		transactionRepository.save(entity);
		//TODO - Ajustar o saldo da conta
	}
	
	@Transactional
	public void draft(DraftDTO dto) {	
		TransactionEntity entity = TransactionEntity.builder().account(null).value(dto.getValue()).build();
		transactionRepository.save(entity);
		//TODO - Ajustar o saldo
	}
	
	@Transactional
	public void transfer(TransferDTO transferDTO){
		
		//TODO - encontrar conta origem
		//TODO - encontrar conta destino
		//TODO - validar saldo origem
		// SENÃO, LANÇAR UMA EXCEPTION
		
		TransactionEntity entityOrigin = TransactionEntity.builder().account(null).value(transferDTO.getValue()).build();
		TransactionEntity entityDestiny = TransactionEntity.builder().account(null).value(transferDTO.getValue()).build();
		//TODO - Ajustar o saldo conta origem
		//TODO - Ajustar o saldo da conta destino
		transactionRepository.saveAll(List.of(entityOrigin,entityDestiny));
	 
	}
	
}
