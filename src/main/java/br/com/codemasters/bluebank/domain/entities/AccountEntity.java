package br.com.codemasters.bluebank.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String number;
	
	@Column(name="balance", nullable = false)
	private double balance;
	
	@ManyToOne
	private AgencyEntity agency;
	
}
