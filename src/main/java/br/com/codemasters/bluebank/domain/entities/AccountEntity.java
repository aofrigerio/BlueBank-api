package br.com.codemasters.bluebank.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseEntityModel {

	@Column(name="number", nullable = false)
	private long number;
	
	@Column(name="balance", nullable = false)
	private double balance;
	
	@ManyToOne
	private AgencyEntity agency;
	
}
