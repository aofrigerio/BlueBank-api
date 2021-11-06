package br.com.codemasters.bluebank.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	
	private Long id;
	private String number;
	private double balance;
	private double limit;
	private Long agency;
	private Long client;

}
