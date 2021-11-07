package br.com.codemasters.bluebank.domain.dtos;

import javax.persistence.Column;

import br.com.codemasters.bluebank.domain.entities.AccountEntity;
import br.com.codemasters.bluebank.domain.enuns.TypeTransactionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	
	private AccountEntity account;
	private TypeTransactionEnum typeTransaction; 	
	private String obs;	

}
