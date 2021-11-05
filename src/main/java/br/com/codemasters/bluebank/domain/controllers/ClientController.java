package br.com.codemasters.bluebank.domain.controllers;

import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping({"/clients"})
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity getClients(){
        return clientService.getClients();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getClientById(@PathVariable("id") Long id){
        return clientService.getClientById(id);
    }

    @PostMapping
    public ResponseEntity<ClientEntity> saveClient(@RequestBody ClientDTO clientDTO){
        ClientEntity client = clientService.save(clientDTO.transformaParaObjeto());
        return ResponseEntity.ok(client);
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
        return clientService.deleteClient(id);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity updateClient(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity){
        return clientService.updateClient(id, clientEntity);
    }

}
