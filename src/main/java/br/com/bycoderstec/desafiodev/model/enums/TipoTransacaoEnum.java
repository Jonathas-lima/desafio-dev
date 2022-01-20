package br.com.bycoderstec.desafiodev.model.enums;

import java.util.Arrays;

public enum TipoTransacaoEnum {

    DEBITO(1, "Débito", "+"),
    BOLETO(2,"Boleto","-"),
    FINANCIAMENTO(3, "Financiamento",  "-"),
    CREDITO(4, "Crédito", "+"  ),
    RECEBIMENTO_EMPRESTIMO(5, "Recebimento Empréstimo","+"),
    VENDAS(6, "Vendas", "+"),
    RECEBIMENTO_TED(7, "Recebimento TED", "+"),
    RECEBIMENTO_DOC(8, "Recebimento DOC", "+"),
    ALUGUEL(9, "Aluguel", "-"),

    VALOR_NAO_MAPEADO(99,"Valor não mapeado", "");



    private int cod;
    private String sinal;
    private String descricao;

    TipoTransacaoEnum(int cod, String descricao, String sinal) {
        this.cod = cod;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }
}
