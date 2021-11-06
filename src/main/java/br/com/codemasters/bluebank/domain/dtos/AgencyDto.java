package br.com.codemasters.bluebank.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyDto {

	private Long id;
	private Long code;
	private String name;
}
