package br.com.codemasters.bluebank.services;

import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @BeforeEach
    void setup(){
        this.clientService = new ClientService(clientRepository);
    }

    @Test
    public void getClients(){
        List<ClientEntity> clientEntityList = new ArrayList<>();
        clientEntityList.add(clientEntity());
        clientEntityList.add(clientEntity2());
        when(clientRepository.findAll()).thenReturn(clientEntityList);
        List<ClientDTO> clientDTOAll =  clientService.getClients();
        assertEquals(clientDTOAll.get(0).getName(),clientEntityList.get(0).getName());
        assertEquals(clientDTOAll.get(1).getName(),clientEntityList.get(1).getName());
        assertEquals(2, clientDTOAll.size());
    }
    @Test
    public void getClientById(){
        when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity()));
        ClientDTO clientDTOById =  clientService.getClientById(1L);
        assertEquals(clientDTOById.getId(),clientEntity().getId());
        assertEquals(clientDTOById.getName(),clientEntity().getName());
        assertEquals(clientDTOById.getAdress(),clientEntity().getAdress());
        assertEquals(clientDTOById.getCpf(),clientEntity().getCpf());
        assertEquals(clientDTOById.getRg(),clientEntity().getRg());
        assertEquals(clientDTOById.getSex(),clientEntity().getSex());
        assertEquals(clientDTOById.getEmail(),clientEntity().getEmail());
    }

    @Test
    public void save(){
        when(clientRepository.save(any())).thenReturn(clientEntity2());
        ClientDTO clientDTOSave = clientService.save(clientDTO2());
        assertEquals(clientDTOSave.getName(),clientEntity2().getName());
    }

    @Test
    public void update(){
        when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity()));
        when(clientRepository.save(any())).thenReturn(clientEntityUpdate());
        ClientDTO clientDTOUpdate = clientService.update(1L,clientDTO());
        assertEquals("Teste update",clientDTOUpdate.getName());
    }

    @Test
    public void delete(){
        when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity()));
        assertDoesNotThrow( () -> clientService.delete(1L));
    }

    ClientEntity clientEntity() {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setName("Teste 01");
        client.setAdress("Adress teste");
        client.setCpf("00913857050");
        client.setRg("1094379641");
        client.setEmail("teste.gmail.com");
        client.setSex("Feminino");
        client.setTelephoneNumber("99583489");
        return client;
    }
    ClientEntity clientEntity2() {
        ClientEntity client = new ClientEntity();
        client.setId(2L);
        client.setName("Teste 02");
        client.setAdress("Adress teste2");
        client.setCpf("00983857050");
        client.setRg("1094879641");
        client.setEmail("teste2.gmail.com");
        client.setSex("Masculino");
        client.setTelephoneNumber("93583489");
        return client;
    }
    ClientEntity clientEntityUpdate() {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setName("Teste update");
        client.setAdress("Adress teste");
        client.setCpf("00913857050");
        client.setRg("1094379641");
        client.setEmail("teste.gmail.com");
        client.setSex("Feminino");
        client.setTelephoneNumber("99583489");
        return client;
    }
    ClientDTO clientDTO() {
        return ClientDTO.builder()
                .id(1L)
                .name("Teste update")
                .adress("Adress teste")
                .cpf("00913857050")
                .rg("1094379641")
                .email("teste.gmail.com")
                .sex("Feminino")
                .telephoneNumber("99583489")
                .build();
    }

    ClientDTO clientDTO2() {
        return ClientDTO.builder()
                .id(2L)
                .name("Teste 02")
                .adress("Adress teste2")
                .cpf("00983857050")
                .rg("1094879641")
                .email("teste2.gmail.com")
                .sex("Masculino")
                .telephoneNumber("93583489")
                .build();
    }


}
