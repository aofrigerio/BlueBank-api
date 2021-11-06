package br.com.codemasters.bluebank.resources;

import java.net.URI;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.codemasters.bluebank.domain.dtos.AgencyDto;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.services.AgencyService;

@RestController()
@RequestMapping("/agency")
public class AgencyResource {
	
	@Autowired
	private AgencyService service;
	
	@GetMapping
	public ResponseEntity<List<AgencyDto>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<AgencyDto> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<AgencyEntity> create(@RequestBody AgencyDto agencyDto){
		AgencyEntity object = service.create(agencyDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<AgencyEntity> update(@PathVariable Long id, @RequestBody AgencyDto agencyDto){
		AgencyEntity object = service.update(id, agencyDto);
		return ResponseEntity.ok().body(object);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
