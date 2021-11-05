package br.com.codemasters.bluebank.domain.dtos;



import br.com.codemasters.bluebank.domain.entities.AgencyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	private long id;
	private long number;
	private double balance;
	private AgencyEntity agency;
}
