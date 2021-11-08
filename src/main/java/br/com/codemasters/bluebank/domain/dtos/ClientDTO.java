package br.com.codemasters.bluebank.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;
    private String adress;
    private String cpf;
    private String rg;
    private String sex;
    private String email;
    private String telephoneNumber;

}
