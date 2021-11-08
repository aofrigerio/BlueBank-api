package br.com.codemasters.bluebank.resources;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codemasters.bluebank.domain.dtos.DepositDTO;
import br.com.codemasters.bluebank.domain.dtos.DraftDTO;
import br.com.codemasters.bluebank.domain.dtos.TransactionDTO;
import br.com.codemasters.bluebank.domain.dtos.TransferDTO;
import br.com.codemasters.bluebank.domain.entities.BaseEntityModel;
import br.com.codemasters.bluebank.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {
	
	private final TransactionService transactionService;
	
	@Autowired
	public TransactionResource(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping
	 public ResponseEntity<Page<TransactionDTO>> getAllTransaction(Pageable pageable){			
       return ResponseEntity.ok(transactionService.getAllTransaction(pageable));
    }
	
	@GetMapping("/{accountNumber}")
	 public ResponseEntity<List<TransactionDTO>> getTransactionByAccount(@PathVariable String accountNumber){				
       return ResponseEntity.ok(transactionService.getByAccountNumber(accountNumber));
    }
	
	@PostMapping("/draft")
    public ResponseEntity<Void> draft(@Valid @RequestBody DraftDTO draftDTO){
		transactionService.draft(draftDTO);
       return ResponseEntity.noContent().build();
    }
	
	@PostMapping("/deposit")
	public ResponseEntity<Void> deposit(@Valid @RequestBody DepositDTO depositDTO){
		transactionService.deposit(depositDTO);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<?> transfer(@Valid @RequestBody TransferDTO transferDTO){
		transactionService.transfer(transferDTO);
		return ResponseEntity.noContent().build();
	}

}
