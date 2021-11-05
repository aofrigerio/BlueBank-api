package br.com.codemasters.bluebank.domain.controllers;

import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import br.com.codemasters.bluebank.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/clients"})
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(){
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok().body(clientService.save(clientDTO));
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id){
         clientService.delete(id);
         return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok().body(clientService.update(id, clientDTO));
    }

}
