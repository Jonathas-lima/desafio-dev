package br.com.bycoderstec.desafiodev.web.rest.response;

import br.com.bycoderstec.desafiodev.model.Loja;
import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.model.enums.TipoTransacaoEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LojaResponse {

    private String nomeLoja;
    private BigDecimal saldo;
    private List<TransacaoResponse> transacoes;

    public static LojaResponse of(Loja loja) {

        BigDecimal soma = BigDecimal.ZERO;
        List<TransacaoResponse> transacoesResponse = new ArrayList<>();

        List<Transacao> transacoes = loja.getTransacoes();

        for (Transacao tr: transacoes) {

            TipoTransacaoEnum tipoTransacao = tr.getTipoTransacao();

            if ("+".equals(tipoTransacao.getSinal())) {
                soma = soma.add(tr.getValor());
            } else {
                soma = soma.subtract(tr.getValor());
            }

            transacoesResponse.add(TransacaoResponse.builder()
                                        .data(tr.getDataOcorrencia())
                                        .desc(tipoTransacao.getDescricao())
                                        .sinal(tipoTransacao.getSinal())
                                        .valor(tr.getValor())
                                        .build()
            );
        }
        return LojaResponse.builder()
               .nomeLoja(loja.getNomeLoja())
                .saldo(soma)
                .transacoes(transacoesResponse)
                .build();
    }
}
