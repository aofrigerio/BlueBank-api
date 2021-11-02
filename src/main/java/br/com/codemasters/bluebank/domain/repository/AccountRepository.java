package br.com.codemasters.bluebank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codemasters.bluebank.domain.entities.AccountEntity;


public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
		
}


