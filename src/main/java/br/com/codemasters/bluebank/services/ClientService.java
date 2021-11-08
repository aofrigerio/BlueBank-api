package br.com.codemasters.bluebank.services;

import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import br.com.codemasters.bluebank.resources.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Optional<ClientEntity> findEntityById(Long id){
        return clientRepository.findById(id);
    }
    
    public List<ClientDTO> getClients(){
        return clientRepository.findAll().stream().map(this::ClientEntityToDto).collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id){
        return ClientEntityToDto(clientRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public ClientDTO save(ClientDTO clientDTO){
        return ClientEntityToDto(clientRepository.save(ClientDtoToEntity(clientDTO)));
    }

    public ClientDTO update(Long id, ClientDTO clientDTO){          
    	  ClientEntity entity = clientRepository.findById(id).orElseThrow(NotFoundException::new);
    	  entity.setId(id);
    	  entity.setName(clientDTO.getName());
    	  entity.setAdress(clientDTO.getAdress());
    	  entity.setCpf(clientDTO.getCpf());
    	  entity.setRg(clientDTO.getRg());
    	  entity.setSex(clientDTO.getSex());
    	  entity.setEmail(clientDTO.getEmail());
    	  entity.setTelephoneNumber(clientDTO.getTelephoneNumber());
          return ClientEntityToDto(clientRepository.save(entity));
    }

    public void delete(Long id){
        if(getClientById(id) != null)
            clientRepository.deleteById(id);
    }

    private ClientDTO ClientEntityToDto (ClientEntity clientEntity) {
        return ClientDTO.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .adress(clientEntity.getAdress())
                .cpf(clientEntity.getCpf())
                .rg(clientEntity.getRg())
                .sex(clientEntity.getSex())
                .email(clientEntity.getEmail())
                .telephoneNumber(clientEntity.getTelephoneNumber())
                .build();
    }

    private ClientEntity ClientDtoToEntity (ClientDTO clientDTO) {
        return ClientEntity.builder()
                .name(clientDTO.getName())
                .adress(clientDTO.getAdress())
                .cpf(clientDTO.getCpf())
                .sex(clientDTO.getSex())
                .email(clientDTO.getEmail())
                .telephoneNumber(clientDTO.getTelephoneNumber())
                .build();
          }
}
