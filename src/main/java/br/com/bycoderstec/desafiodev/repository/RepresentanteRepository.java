package br.com.bycoderstec.desafiodev.repository;

import br.com.bycoderstec.desafiodev.model.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {

}
