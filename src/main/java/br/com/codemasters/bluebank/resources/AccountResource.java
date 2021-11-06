package br.com.codemasters.bluebank.resources;

import java.net.URI;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.codemasters.bluebank.domain.dtos.AccountDto;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.services.AccountService;

@RestController()
@RequestMapping("/account")
public class AccountResource {
	
	@Autowired
	private AccountService service;
		
	@GetMapping
	public ResponseEntity<List<AccountDto>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<AccountDto> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<AccountEntity> create(@RequestBody AccountDto accountDto){
		AccountEntity object = service.create(accountDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<AccountEntity> update(@PathVariable Long id, @RequestBody AccountDto accountDto){
		AccountEntity object = service.update(id, accountDto);
		return ResponseEntity.ok().body(object);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
