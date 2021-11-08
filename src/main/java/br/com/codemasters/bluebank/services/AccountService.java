package br.com.codemasters.bluebank.services;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codemasters.bluebank.domain.dtos.AccountDTO;
import br.com.codemasters.bluebank.domain.dtos.AgencyDTO;
import br.com.codemasters.bluebank.domain.dtos.BalanceDTO;
import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.AccountRepository;
import br.com.codemasters.bluebank.resources.exceptions.NotFoundException;

import br.com.codemasters.bluebank.resources.exceptions.FundsNotAcceptException;


@Service
public class AccountService {
	
	@Autowired
	private final AccountRepository repository;
	
	
	@Autowired
	private final AgencyService agencyService;
	
	@Autowired
	private final ClientService clientService;
	
	public AccountService(AccountRepository accountRepository, AgencyService agencyService, ClientService clientService) {
		this.repository = accountRepository;
		this.agencyService = agencyService;
		this.clientService = clientService;
	}

	public List<AccountDTO> findAll(){
		return repository.findAll().stream().map(this::accountEntityToDto).collect(Collectors.toList());
	}

	public AccountEntity create(AccountDTO accountDto){
	    AgencyEntity agencyEntity = agencyService.findEntitytById(accountDto.getAgency()).orElseThrow(NotFoundException::new);
	    ClientEntity clientEntity = clientService.findEntityById(accountDto.getClient()).orElseThrow(NotFoundException::new);
		return repository.save(AccountEntity.builder().agency(agencyEntity).balance(accountDto.getBalance()).client(clientEntity).limit(accountDto.getLimit()).build());
	}
	
	public AccountDTO findById(Long accountId) {
		AccountEntity account = repository.findById(accountId).orElseThrow(NotFoundException::new);
		return accountEntityToDto(account);
	}
	
	public AccountEntity findEntityById(Long accountId) {
		return repository.findById(accountId).orElseThrow(NotFoundException::new);
	}
	
	public AccountEntity findByAccount(String number) {
		return repository.findByNumber(number).orElseThrow(NotFoundException::new);
	}
	
	public AccountEntity update(Long id, AccountDTO obj) {
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
		Double limit = entity.getLimit();

		if((balance+limit) - value < 0) {
			throw new FundsNotAcceptException();
		}
				
		entity.setBalance(balance - value);
		repository.save(entity);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public BalanceDTO getBalance(Long idClient, String accountNumber) {
		AccountEntity accountEntity = repository.findByNumber(accountNumber).orElseThrow(NotFoundException::new);		
		ClientDTO clientDTO = clientService.getClientById(idClient);
		AgencyDTO agencyDto = agencyService.findById(accountEntity.getAgency().getId());
		return BalanceDTO.builder()
					.client(clientDTO)
					.agency(agencyDto)					
					.account(accountEntityToDto(accountEntity))
					.build();
	}
	
	private AccountDTO accountEntityToDto(AccountEntity account) {
		return AccountDTO.builder()
				.id(account.getId())
				.number(account.getNumber())
				.balance(account.getBalance())
				.agency(account.getAgency().getId())
				.limit(account.getLimit())
				.client(account.getClient().getId())
				.build();	
	}
}
