package br.com.bycoderstec.desafiodev.model.enums;

import java.util.Arrays;

public enum TipoTransacaoEnum {

    DEBITO(1, "+"),
    BOLETO(2, "-"),
    FINANCIAMENTO(3,  "-"),
    CREDITO(4, "+"  ),
    RECEBIMENTO_EMPRESTIMO(5, "+"),
    VENDAS(6, "+"),
    RECEBIMENTO_TED(7, "+"),
    RECEBIMENTO_DOC(8, "+"),
    ALUGUEL(9, "-"),

    VALOR_NAO_MAPEADO(99, "");



    private int cod;
    private String sinal;

    TipoTransacaoEnum(int cod, String sinal) {
        this.cod = cod;
        this.sinal = sinal;
    }

    public static TipoTransacaoEnum getByCod(String cod) {
        return Arrays.stream(values()).filter(value -> value.getCod() == Integer.parseInt(cod)).findFirst().orElse(TipoTransacaoEnum.VALOR_NAO_MAPEADO);
    }

    public int getCod() {
        return cod;
    }

    public String getSinal() {
        return sinal;
    }
}
