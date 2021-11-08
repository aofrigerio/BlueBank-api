package br.com.codemasters.bluebank.domain.dtos;


import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	
	private Long id;
	private String number;
	
	@Min(value = 0)
	private double balance;
	
	@Min(value = 0)
	private double limit;
	private Long agency;
	private Long client;

}
