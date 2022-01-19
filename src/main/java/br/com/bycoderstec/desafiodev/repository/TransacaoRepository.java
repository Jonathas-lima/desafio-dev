package br.com.bycoderstec.desafiodev.repository;

import br.com.bycoderstec.desafiodev.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository  extends JpaRepository<Transacao, Long> {

}
