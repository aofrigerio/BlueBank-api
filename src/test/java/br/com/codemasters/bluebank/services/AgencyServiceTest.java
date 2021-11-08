package br.com.codemasters.bluebank.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.codemasters.bluebank.domain.dtos.AgencyDTO;
import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.domain.repository.AgencyRepository;

@ExtendWith(MockitoExtension.class)
class AgencyServiceTest {

	AgencyService agencyService;
	
	
	@Mock
	AgencyRepository agencyRepository;


    @BeforeEach
    void setup(){
        this.agencyService = new AgencyService(agencyRepository);
    }
    
    @Test
    public void findAll() {
    	List<AgencyEntity> agencyEntityList = new ArrayList<>();
    	agencyEntityList.add(agencyEntity());
    	agencyEntityList.add(agencyEntity2());
        when(agencyRepository.findAll()).thenReturn(agencyEntityList);
        List<AgencyDTO> agencyDTOAll =  agencyService.findAll();
        assertEquals(agencyDTOAll.get(0).getName(),agencyEntityList.get(0).getName());
        assertEquals(agencyDTOAll.get(1).getName(),agencyEntityList.get(1).getName());
        assertEquals(2, agencyDTOAll.size());
    }
    
    @Test
    public void findEntitytById(){
        when(agencyService.findEntitytById(1L)).thenReturn(Optional.of(agencyEntity()));
        assertDoesNotThrow( () -> agencyService.findEntitytById(1l));
    }
    
    @Test
    public void findById(){
        when(agencyRepository.findById(1L)).thenReturn(Optional.of(agencyEntity()));
        AgencyDTO agencyDTOById =  agencyService.findById(1L);
        assertEquals(agencyDTOById.getId(),agencyEntity().getId());
        assertEquals(agencyDTOById.getCode(),agencyEntity().getCode());
        assertEquals(agencyDTOById.getName(),agencyEntity().getName());
    }
    
    @Test
    public void create(){
        when(agencyRepository.save(any())).thenReturn(agencyEntity2());
        AgencyEntity agencyDTOSave = agencyService.create(agencyDTO());
        assertEquals(agencyDTOSave.getName(),agencyEntity2().getName());
    }
    
    @Test
    public void update(){
        when(agencyRepository.findById(1L)).thenReturn(Optional.of(agencyEntity()));
        when(agencyRepository.save(any())).thenReturn(agencyEntity());
        AgencyEntity agencyDTOUpdate = agencyService.update(1L,agencyDTO());
        assertEquals("Agência Butiá-RS",agencyDTOUpdate.getName());
    }

    @Test
    public void delete(){
        when(agencyRepository.findById(1L)).thenReturn(Optional.of(agencyEntity()));
        assertDoesNotThrow( () -> agencyService.delete(1L));
    }
    
    AgencyEntity agencyEntity() {
		return AgencyEntity.builder().code(1368L).name("Agência Butiá-RS").build();
	}
    
    AgencyEntity agencyEntity2() {
		return AgencyEntity.builder().code(1679L).name("Agência São Paulo-SP").build();
	}
    
    AgencyDTO agencyDTO() {
  		return AgencyDTO.builder().code(1679L).name("Agência São Paulo-SP").build();
  	}
    
	
}
