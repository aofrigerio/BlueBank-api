package br.com.codemasters.bluebank.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codemasters.bluebank.domain.entities.AccountEntity;


public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
	
	Optional<AccountEntity> findByNumber(String number);
		
}


