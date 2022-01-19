package br.com.bycoderstec.desafiodev.model.enums;

public enum TipoDadoEnum {

    TIPO_TRANSACAO(1,1),
    DATA_OCORRENCIA(2, 8),
    VALOR(10, 10),
    CPF(20, 11),
    NUM_CARTAO(31, 12),
    HORA_OCORRENCIA(43, 6),
    DONO_DA_LOJA(49, 14),
    NOME_LOJA(63, 18);

    private int inicio;
    private int tamanho;

    TipoDadoEnum(int inicio, int tamanho) {
        this.inicio = inicio;
        this.tamanho = tamanho;
    }

    public int getInicio() {
        return inicio;
    }

    public int getTamanho() {
        return tamanho;
    }
}
