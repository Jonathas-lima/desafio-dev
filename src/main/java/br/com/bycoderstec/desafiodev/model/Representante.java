package br.com.bycoderstec.desafiodev.model;

import lombok.*;

import javax.persistence.*;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Representante {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nome;

    private String cpf;

    @OneToOne(mappedBy = "representante")
    private Loja loja;

    @Override
    public String toString() {
        return "Representante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", loja=" + loja +
                '}';
    }
}
