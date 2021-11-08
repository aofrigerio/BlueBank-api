package br.com.codemasters.bluebank.domain.dtos;


import br.com.codemasters.bluebank.domain.entities.AgencyEntity.AgencyEntityBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyDTO {

	private Long id;
	private Long code;
	private String name;

}
