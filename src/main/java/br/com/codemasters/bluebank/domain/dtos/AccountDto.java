package br.com.codemasters.bluebank.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	private Long id;
	private Long number;
	private double balance;
	private Long agency;

}
