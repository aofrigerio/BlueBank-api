package br.com.codemasters.bluebank.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.codemasters.bluebank.domain.dtos.BalanceDTO;
import br.com.codemasters.bluebank.services.AccountService;

@RestController()
@RequestMapping("/balance")
public class BalanceResource {
	
	@Autowired
	private AccountService accountService;
		
	@GetMapping("/{idClient}")
	public ResponseEntity<BalanceDTO> getBalance(@PathVariable Long idClient, @RequestParam String accountNumber){
		return ResponseEntity.ok().body(accountService.getBalance(idClient, accountNumber));
	}
	
}
