package br.com.codemasters.bluebank.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.com.codemasters.bluebank.domain.enuns.TypeTransactionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity extends BaseEntityModel {
	
	@ManyToOne
	private AccountEntity account;
	
	@Column(name="value", nullable= false)
	private Double value;	
	
	@Enumerated(EnumType.STRING)
	@Column(name="type", nullable=false, length = 15)
	private TypeTransactionEnum typeTransaction; 
	
	@Column(name="obs")
	private String obs;	
	
}
