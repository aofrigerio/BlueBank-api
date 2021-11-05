package br.com.codemasters.bluebank.services;

import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientEntity save(ClientEntity clientEntity){
        return clientRepository.save(clientEntity);
    }
    public ResponseEntity getClients(){
        return ResponseEntity.ok(clientRepository.findAll());
    }

    public ResponseEntity getClientById(Long id){
        return clientRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteClient(Long id){
        return clientRepository.findById(id)
                .map(record -> {
                    clientRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity updateClient(Long id, ClientEntity clientEntity){
        return clientRepository.findById(id)
                .map(record -> {
                    record.setName(clientEntity.getName());
                    record.setAdress(clientEntity.getAdress());
                    record.setCpf(clientEntity.getCpf());
                    record.setRg(clientEntity.getRg());
                    record.setSex(clientEntity.getSex());
                    record.setEmail(clientEntity.getEmail());
                    record.setTelephoneNumber(clientEntity.getTelephoneNumber());
                    ClientEntity updated = clientRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
