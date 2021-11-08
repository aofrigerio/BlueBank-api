package br.com.codemasters.bluebank.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.codemasters.bluebank.domain.dtos.AgencyDTO;
import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import br.com.codemasters.bluebank.domain.repository.AgencyRepository;
import br.com.codemasters.bluebank.resources.exceptions.NotFoundException;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository repository;
	
	public AgencyService(AgencyRepository agencyRepository) {
		this.repository = agencyRepository;
	}

	public List<AgencyDTO> findAll(){
		return repository.findAll().stream().map(this::agencyEntityToDto).collect(Collectors.toList());
	}
	
	public Optional<AgencyEntity> findEntitytById(Long id) {
		return repository.findById(id);
	}
	
	public AgencyEntity create(AgencyDTO agencyDto){		
		return repository.save(agencyDtoToEntity(agencyDto));
	}
	
	public AgencyDTO findById(Long agencyId) {
		AgencyEntity agency = repository.findById(agencyId).orElseThrow(NotFoundException::new);
		return agencyEntityToDto(agency);
	}
	
	public AgencyEntity update(Long id, AgencyDTO obj) {
		AgencyEntity newObj = repository.findById(id).orElseThrow(NotFoundException::new);
		newObj.setCode(obj.getCode());
		newObj.setName(obj.getName());
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	private AgencyDTO agencyEntityToDto(AgencyEntity agency) {
		AgencyDTO agencyDto = new AgencyDTO();
		agencyDto.setId(agency.getId());
		agencyDto.setCode(agency.getCode());
		agencyDto.setName(agency.getName());
		return agencyDto;
	}	
	
	private AgencyEntity agencyDtoToEntity(AgencyDTO agencyDto) {
		return AgencyEntity.builder()
				.code(agencyDto.getCode())
				.name(agencyDto.getName())
				.build();
	}
	
	
}
