package br.com.codemasters.bluebank.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
