package br.com.codemasters.bluebank.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codemasters.bluebank.domain.entities.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
		
}


