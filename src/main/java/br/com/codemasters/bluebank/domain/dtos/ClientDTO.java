package br.com.codemasters.bluebank.domain.dtos;

import br.com.codemasters.bluebank.domain.entities.ClientEntity;

public class ClientDTO {

    private String name;

    private String adress;

    private String cpf;

    private String rg;

    private String sex;

    private String email;

    private String telephoneNumber;

    public ClientEntity transformaParaObjeto(){
        return new ClientEntity(name, adress, cpf, rg, sex, email, telephoneNumber);
    }
}
