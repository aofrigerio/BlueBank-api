package br.com.codemasters.bluebank.domain.repository;

import br.com.codemasters.bluebank.domain.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
