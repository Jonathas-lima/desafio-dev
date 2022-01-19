package br.com.bycoderstec.desafiodev.repository;

import br.com.bycoderstec.desafiodev.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

    Optional<Loja> findByNomeLoja(String desc);
}
