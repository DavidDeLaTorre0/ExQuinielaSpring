package org.agaray.exQuiniela.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.agaray.exQuiniela.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
	public Collection<Partido> findByFecha(LocalDate fecha);
	public Collection<Partido> findBynJornadaOrderByFechaAsc(int nJornada);
}
