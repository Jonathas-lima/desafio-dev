package br.com.bycoderstec.desafiodev.model;

import br.com.bycoderstec.desafiodev.model.enums.TipoTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private TipoTransacaoEnum tipoTransacao;

    private String dataOcorrencia;

    private String horaOcorrencia;

    private BigDecimal valor;

    private String cartaoUtilizado;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", tipoTransacao=" + tipoTransacao +
                ", dataOcorrencia='" + dataOcorrencia + '\'' +
                ", horaOcorrencia='" + horaOcorrencia + '\'' +
                ", valor=" + valor +
                ", cartaoUtilizado='" + cartaoUtilizado + '\'' +
                ", loja=" + loja +
                '}';
    }
}
