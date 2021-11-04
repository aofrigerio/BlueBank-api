package br.com.codemasters.bluebank.domain.controllers;

import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.valueOf(200));
    }

    @GetMapping(path = "{/id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id){
       if(clientRepository.existsById(id))
         return new ResponseEntity<>(clientRepository.findById(id), HttpStatus.valueOf(200));
       return new ResponseEntity<>(HttpStatus.valueOf(404));
    }

    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody ClientEntity clientEntity){
        return new ResponseEntity<>(clientRepository.save(clientEntity), HttpStatus.valueOf(200));
    }

    @DeleteMapping(path ="{/id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody ClientEntity clientEntity){
        return new ResponseEntity<>(clientRepository.save(clientEntity), HttpStatus.valueOf(200));
    }

}
