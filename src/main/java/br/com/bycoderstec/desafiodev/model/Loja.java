package br.com.bycoderstec.desafiodev.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@EqualsAndHashCode
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Loja {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nomeLoja;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "representante_id", referencedColumnName = "id")
    private Representante representante;

    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacoes;

    @Override
    public String toString() {
        return "Loja{" +
                "id=" + id +
                ", nomeLoja='" + nomeLoja + '\'' +
                '}';
    }
}
