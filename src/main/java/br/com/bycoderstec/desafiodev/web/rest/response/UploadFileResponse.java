package br.com.bycoderstec.desafiodev.web.rest.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UploadFileResponse {

    private List<Integer> idsTransacoes;
}
