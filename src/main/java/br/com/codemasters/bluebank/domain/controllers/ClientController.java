package br.com.codemasters.bluebank.domain.controllers;

import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public ResponseEntity<?> getClients(){
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping(path = "{/id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok(clientRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody ClientEntity clientEntity){
        return ResponseEntity.ok(clientRepository.save(clientEntity));
    }

    @DeleteMapping(path ="{/id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody ClientEntity clientEntity){
        return ResponseEntity.ok(clientRepository.save(clientEntity));
    }

}
