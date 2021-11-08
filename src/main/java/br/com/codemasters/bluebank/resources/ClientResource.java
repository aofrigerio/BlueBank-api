package br.com.codemasters.bluebank.resources;

import br.com.codemasters.bluebank.domain.dtos.ClientDTO;
import br.com.codemasters.bluebank.services.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/customers"})
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ApiOperation(value="Return a list with all customers", response = ClientDTO.class)
    public ResponseEntity<List<ClientDTO>> getClients(){
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value="Returns a client by id", response = ClientDTO.class)
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @PostMapping
    @ApiOperation(value="Create a client", response = ClientDTO.class)
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok().body(clientService.save(clientDTO));
    }

    @DeleteMapping(path ="/{id}")
    @ApiOperation(value="Delete a client by id", response = ClientDTO.class)
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id){
         clientService.delete(id);
         return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    @ApiOperation(value="Update a customer by id", response = ClientDTO.class)
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok().body(clientService.update(id, clientDTO));
    }

}
