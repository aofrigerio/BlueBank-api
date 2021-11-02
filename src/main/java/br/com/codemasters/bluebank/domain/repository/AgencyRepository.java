package br.com.codemasters.bluebank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codemasters.bluebank.domain.entities.AgencyEntity;

public interface AgencyRepository extends JpaRepository <AgencyEntity, Long>{
	

}
