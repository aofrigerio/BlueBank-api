package br.com.codemasters.bluebank.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyEntity extends BaseEntityModel {


	@Column(name="code", nullable= false)
	private long code;
	
	@Column(name="name", nullable = false)
	private String name;

}
