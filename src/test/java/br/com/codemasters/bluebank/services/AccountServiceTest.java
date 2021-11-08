package br.com.codemasters.bluebank.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.codemasters.bluebank.domain.dtos.AccountDTO;
import br.com.codemasters.bluebank.domain.dtos.AgencyDTO;
import br.com.codemasters.bluebank.domain.dtos.BalanceDTO;
import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.AccountRepository;
import br.com.codemasters.bluebank.domain.repository.AgencyRepository;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
	@Mock
	AgencyService agencyService;
	
	@Mock
	ClientService clientService;

	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	ClientRepository clientRepository;
	 
	@Mock
	AgencyRepository agencyRepository;

    @BeforeEach
    void setup(){
        this.accountService = new AccountService(accountRepository, agencyService, clientService);
        /*this.agencyService = new AgencyService(agencyRepository);*/
        //this.clientService = new ClientService(clientRepository);
    }
    
    @Test
    public void findAll() {
    	List<AccountEntity> accountEntityList = new ArrayList<>();
    	accountEntityList.add(accountEntity());
    	accountEntityList.add(accountEntity2());
        when(accountRepository.findAll()).thenReturn(accountEntityList);
        List<AccountDTO> accountDTOAll =  accountService.findAll();
        assertEquals(accountDTOAll.get(0).getNumber(),accountEntityList.get(0).getNumber());
        assertEquals(accountDTOAll.get(1).getNumber(),accountEntityList.get(1).getNumber());
        assertEquals(2, accountDTOAll.size());
    }
    
    @Test
    public void findById(){
        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEntity()));
        AccountDTO accountDTOById =  accountService.findById(1L);
        assertEquals(accountDTOById.getId(),accountEntity().getId());
        assertEquals(accountDTOById.getNumber(),accountEntity().getNumber());
    }
    
    @Test
    public void findEntityById(){
        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEntity()));
        assertDoesNotThrow( () -> accountService.findEntityById(1L));
    }
    
    @Test
    public void findByAccount(){
        when(accountRepository.findByNumber("35691226-3")).thenReturn(Optional.of(accountEntity()));
        assertDoesNotThrow( () -> accountService.findByAccount("35691226-3"));
    }
    
    @Test
    public void create(){	
    	when(agencyService.findEntitytById(agencyEntity().getId())).thenReturn(Optional.of(agencyEntity()));
    	when(clientService.findEntityById(clientEntity().getId())).thenReturn(Optional.of(clientEntity()));
        assertDoesNotThrow( () -> accountService.create(accountDTO()));
    }
    
    @Test
    public void update(){
    	when(agencyService.findEntitytById(agencyEntity().getId())).thenReturn(Optional.of(agencyEntity()));
        assertDoesNotThrow( () -> accountService.update(accountDTO().getId(), accountDTO()));
    }
    
    @Test
    public void depositUpdate(){
    	when(accountRepository.findById(accountEntity().getId())).thenReturn(Optional.of(accountEntity()));
        assertDoesNotThrow( () -> accountService.depositUpdate(accountDTO().getId(), 300D));
    }
    
    
    @Test
    public void draftUpdate(){
    	when(accountRepository.findById(accountEntity().getId())).thenReturn(Optional.of(accountEntity()));
        assertDoesNotThrow( () -> accountService.draftUpdate(accountDTO().getId(), 300D));
    }
    
    
    @Test
    public void getBalance(){	
    	when(accountRepository.findByNumber(accountEntity().getNumber())).thenReturn(Optional.of(accountEntity()));
    	when(agencyService.findEntitytById(agencyEntity().getId())).thenReturn(Optional.of(agencyEntity()));
    	when(clientService.getClientById(clientEntity().getId())).thenReturn(clientDTO());
        assertDoesNotThrow( () -> accountService.create(accountDTO()));
    }

    @Test
    public void delete(){
        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEntity()));
        assertDoesNotThrow( () -> accountService.delete(1L));
    }
    
    AgencyDTO agencydTO() {
		return AgencyDTO.builder().code(1368L).name("Agência Butiá-RS").build();
	}
    
    AgencyEntity agencyEntity() {
		return AgencyEntity.builder().code(1368L).name("Agência Butiá-RS").build();
	}
    
    ClientEntity clientEntity() {
       return ClientEntity.builder().name("Teste 01").adress("Adress teste").cpf("00913857050").rg("1094379641").email("teste.gmail.com").sex("Feminino").telephoneNumber("99583489").build();
    }
    
    ClientDTO clientDTO() {
        return ClientDTO.builder().name("Teste 01").adress("Adress teste").cpf("00913857050").rg("1094379641").email("teste.gmail.com").sex("Feminino").telephoneNumber("99583489").build();
     }
    
    AccountEntity accountEntity() {
		return AccountEntity.builder().number("35691226-3").balance(1000D).limit(3000D).agency(agencyEntity()).client(clientEntity()).build();
	}
    
    AccountEntity accountEntity2() {
 		return AccountEntity.builder().number("39571-6").balance(2000D).limit(4000D).agency(agencyEntity()).client(clientEntity()).build();
 	}
    
    AccountDTO accountDTO() {
 		return AccountDTO.builder().number("39571-6").balance(2000D).limit(4000D).agency(agencyEntity().getId()).client(1l).build();
 	}
    
    BalanceDTO balanceDTO() {
    	return BalanceDTO.builder().client(clientDTO()).agency(agencydTO()).account(accountDTO()).build();
    }
   
    
}
