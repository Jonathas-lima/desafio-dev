package br.com.bycoderstec.desafiodev.repository;

import br.com.bycoderstec.desafiodev.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

}
