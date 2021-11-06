package br.com.codemasters.bluebank.services;

import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    public Optional<ClientEntity> findEntityById(Long id){
        return clientRepository.findById(id);
    }
    
    public List<ClientDTO> getClients(){
        return clientRepository.findAll().stream().map(this::ClientEntityToDto).collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id){
        return ClientEntityToDto(clientRepository.findById(id).orElseThrow(null));
    }

    public ClientDTO save(ClientDTO clientDTO){
        return ClientEntityToDto(clientRepository.save(ClientDtoToEntity(clientDTO)));
    }

    public void delete(Long id){
        if(getClientById(id) != null)
            clientRepository.deleteById(id);
    }

    public ClientDTO update(Long id, ClientDTO clientDTO){
          getClientById(id);
          return ClientEntityToDto(clientRepository.save(ClientDtoToEntity(clientDTO)));

    }

    private ClientDTO ClientEntityToDto (ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientEntity.getId());
        clientDTO.setName(clientEntity.getName());
        clientDTO.setAdress(clientEntity.getAdress());
        clientDTO.setCpf(clientEntity.getCpf());
        clientDTO.setRg(clientEntity.getRg());
        clientDTO.setSex(clientEntity.getSex());
        clientDTO.setEmail(clientEntity.getEmail());
        clientDTO.setTelephoneNumber(clientEntity.getTelephoneNumber());
        return clientDTO;
    }

    private ClientEntity ClientDtoToEntity (ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientDTO.getId());
        clientEntity.setName(clientDTO.getName());
        clientEntity.setAdress(clientDTO.getAdress());
        clientEntity.setCpf(clientDTO.getCpf());
        clientEntity.setRg(clientDTO.getRg());
        clientEntity.setSex(clientDTO.getSex());
        clientEntity.setEmail(clientDTO.getEmail());
        clientEntity.setTelephoneNumber(clientDTO.getTelephoneNumber());
        return clientEntity;
    }
}
