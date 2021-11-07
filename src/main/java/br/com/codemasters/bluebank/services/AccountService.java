package br.com.codemasters.bluebank.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codemasters.bluebank.domain.dtos.AccountDto;
import br.com.codemasters.bluebank.domain.dtos.AgencyDto;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.AccountRepository;
import br.com.codemasters.bluebank.domain.repository.AgencyRepository;
import br.com.codemasters.bluebank.resources.exceptions.NotFoundException;
import br.com.codemasters.bluebank.resources.exceptions.FundsNotAcceptException;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ClientService clientService;
	
	public List<AccountDto> findAll(){
		return repository.findAll().stream().map(this::AccountEntityToDto).collect(Collectors.toList());
	}
	
	public AccountEntity create(AccountDto accountDto){
	    AgencyEntity agencyEntity = agencyService.findEntitytById(accountDto.getAgency()).orElseThrow(NotFoundException::new);
	    ClientEntity clientEntity = clientService.findEntityById(accountDto.getClient());
		return repository.save(AccountEntity.builder().agency(agencyEntity).balance(accountDto.getBalance()).client(clientEntity).limit(accountDto.getLimit()).build());
	}
	
	public AccountDto findById(Long accountId) {
		AccountEntity account = repository.findById(accountId).orElseThrow(NotFoundException::new);
		return AccountEntityToDto(account);
	}
	
	public AccountEntity findEntityById(Long accountId) {
		return repository.findById(accountId).orElseThrow(NotFoundException::new);
	}
	
	public AccountEntity update(Long id, AccountDto obj) {
		AccountEntity entity = repository.findById(id).get();
		AgencyEntity entityAgency = agencyService.findEntitytById(obj.getAgency()).get();
		
		return repository.save(AccountEntity.builder()
				.number(obj.getNumber())
				.balance(obj.getBalance())
				.limit(obj.getLimit())
				.agency(entityAgency)
				.build());
	}
	
	public void depositUpdate(Long accountId, Double value) {
		AccountEntity entity = repository.findById(accountId).orElseThrow(NotFoundException::new);		
		Double balance = entity.getBalance();
		entity.setBalance(balance + value);
		repository.save(entity);
	}		
	
	public void draftUpdate(Long accountId, Double value) {
		AccountEntity entity = repository.findById(accountId).orElseThrow(NotFoundException::new);		
		Double balance = entity.getBalance();
		
		if(balance - value < 0) {
			throw new FundsNotAcceptException();
		}
				
		entity.setBalance(balance - value);
		repository.save(entity);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	private AccountDto AccountEntityToDto (AccountEntity account) {
		return AccountDto.builder()
				.id(account.getId())
				.number(account.getNumber())
				.balance(account.getBalance())
				.agency(account.getAgency().getId())
				.build();	
	}
}
