package br.com.bycoderstec.desafiodev.web.rest.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransacaoResponse {

    private String desc;
    private String data;
    private BigDecimal valor;
    private String sinal;
}
