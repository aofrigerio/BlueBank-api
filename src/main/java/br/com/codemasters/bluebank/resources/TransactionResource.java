package br.com.codemasters.bluebank.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codemasters.bluebank.domain.dtos.DepositDTO;
import br.com.codemasters.bluebank.domain.dtos.DraftDTO;
import br.com.codemasters.bluebank.domain.dtos.TransferDTO;
import br.com.codemasters.bluebank.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {
	
	private final TransactionService transactionService;
	
	@Autowired
	public TransactionResource(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping("/draft")
    public ResponseEntity<Void> draft(DraftDTO draftDTO){
		transactionService.draft(draftDTO);
       return ResponseEntity.noContent().build();
    }
	
	@PostMapping("/deposit")
	public ResponseEntity<Void> deposit(DepositDTO depositDTO){
		transactionService.deposit(depositDTO);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<?> transfer(TransferDTO transferDTO){
		transactionService.transfer(transferDTO);
		return ResponseEntity.noContent().build();
	}

}
