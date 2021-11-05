package br.com.codemasters.bluebank.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.codemasters.bluebank.domain.dtos.AgencyDto;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.domain.repositories.AgencyRepository;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository repository;
	
	
	public List<AgencyDto> findAll(){
		return repository.findAll().stream().map(this::AgencyEntityToDto).collect(Collectors.toList());
	}
	
	
	
	public AgencyEntity  create(AgencyEntity agency){
		agency.setId(null);
		return repository.save(agency);
	}
	
	
	public AgencyDto findById ( Long agencyId) {
		AgencyEntity agency = repository.findById(agencyId).orElseThrow(null);
		return AgencyEntityToDto(agency);
	}
	
	
	public AgencyEntity update(Long id, AgencyEntity obj) {
		AgencyEntity newObj = repository.findById(id).orElseThrow(null);
		newObj.setCode(obj.getCode());
		newObj.setName(obj.getName());
		return repository.save(newObj);
	}
	
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	
	private AgencyDto AgencyEntityToDto (AgencyEntity agency) {
		AgencyDto agencyDto = new AgencyDto();
		agencyDto.setId(agency.getId());
		agencyDto.setCode(agency.getCode());
		agencyDto.setName(agency.getName());
		return agencyDto;
	}



	
}
