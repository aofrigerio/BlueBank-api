package br.com.codemasters.bluebank.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.codemasters.bluebank.domain.dtos.AgencyDto;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.domain.repository.AgencyRepository;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository repository;
	
	public List<AgencyDto> findAll(){
		return repository.findAll().stream().map(this::agencyEntityToDto).collect(Collectors.toList());
	}
	
	public Optional<AgencyEntity> findEntitytById(Long id) {
		return repository.findById(id);
	}
	
	public AgencyEntity  create(AgencyDto agencyDto){		
		return repository.save(agencyDtoToEntity(agencyDto));
	}
	
	public AgencyDto findById(Long agencyId) {
		AgencyEntity agency = repository.findById(agencyId).orElseThrow(null);
		return agencyEntityToDto(agency);
	}
	
	public AgencyEntity update(Long id, AgencyDto obj) {
		AgencyEntity newObj = repository.findById(id).orElseThrow(null);
		newObj.setCode(obj.getCode());
		newObj.setName(obj.getName());
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	private AgencyDto agencyEntityToDto(AgencyEntity agency) {
		AgencyDto agencyDto = new AgencyDto();
		agencyDto.setId(agency.getId());
		agencyDto.setCode(agency.getCode());
		agencyDto.setName(agency.getName());
		return agencyDto;
	}	
	
	private AgencyEntity agencyDtoToEntity(AgencyDto agencyDto) {
		return AgencyEntity.builder()
				.code(agencyDto.getCode())
				.name(agencyDto.getName())
				.build();
	}
	
	
}
