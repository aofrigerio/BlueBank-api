package br.com.codemasters.bluebank.domain.controllers;

import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/clients"})
public class ClientController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public ResponseEntity getClients(){
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getClientById(@PathVariable("id") Long id){
        return clientRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientEntity clientEntity){
        return ResponseEntity.ok(clientRepository.save(clientEntity));
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
        return clientRepository.findById(id)
                .map(record -> {
                    clientRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity updateClient(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity){
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
