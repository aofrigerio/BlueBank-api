package br.com.codemasters.bluebank.domain.dtos;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferDTO {
	
	private String accountIdOrigin;
	private String accountIdDestiny;
	@Min(value = 0)
	private Double value;
	private String obs;

}
