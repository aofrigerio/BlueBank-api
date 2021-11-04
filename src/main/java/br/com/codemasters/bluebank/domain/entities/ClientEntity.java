package br.com.codemasters.bluebank.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity extends BaseEntityModel {

    @Column(name = "name")
    private String name;

    @Column(name = "adress")
    private String adress;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "email")
    private String email;

    @Column(name = "telephoneNumber")
    private String telephoneNumber;

}
