package br.com.codemasters.bluebank.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codemasters.bluebank.domain.dtos.AccountDto;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.repositories.AccountRepository;
import br.com.codemasters.bluebank.domain.repositories.AgencyRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private AgencyRepository agencyRepository;
	
	public List<AccountDto> findAll(){
		return repository.findAll().stream().map(this::AccountEntityToDto).collect(Collectors.toList());
	}
	
	public AccountEntity  create(AccountEntity account){
		account.setId(null);
		agencyRepository.findById(account.getAgency()).orElseThrow(null);
		return repository.save(account);
	}
	
	public AccountDto findById ( Long accountId) {
		AccountEntity account = repository.findById(accountId).orElseThrow(null);
		return AccountEntityToDto(account);
	}
	
	public AccountEntity update(Long id, AccountEntity obj) {
		AccountEntity newObj = repository.findById(id).orElseThrow(null);
		agencyRepository.findById(obj.getAgency()).orElseThrow();
		newObj.setNumber(obj.getNumber());
		newObj.setBalance(obj.getBalance());
		newObj.setAgency(obj.getAgency());
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	private AccountDto AccountEntityToDto (AccountEntity account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setNumber(account.getNumber());
		accountDto.setBalance(account.getBalance());
		accountDto.setAgency(account.getAgency());
		return accountDto;
	}
}
