package br.com.codemasters.bluebank.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity extends BaseEntityModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "adress", nullable = false)
    private String adress;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "sexo", nullable = false)
    private String sex;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephoneNumber", nullable = false)
    private String telephoneNumber;

    @ManyToOne
    private AccountEntity account;

}
