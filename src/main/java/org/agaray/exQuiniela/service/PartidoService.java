package org.agaray.exQuiniela.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.agaray.exQuiniela.entities.Partido;
import org.agaray.exQuiniela.repository.EquipoRepository;
import org.agaray.exQuiniela.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private EquipoRepository equipoRepository;

	public Collection<Partido> findAll() {
		return partidoRepository.findAll();
	}

	public void save(int nJornada, LocalDate fecha, int gl, int gv, Long idLocal, Long idVisitante) {
		
		Partido partido = new Partido();
		
		partido.setnJornada(nJornada);
		partido.setFecha(fecha);
		partido.setGl(gl);
		partido.setGv(gv);
		
		//local
		partido.setLocal(equipoRepository.getById(idLocal));
		//visitante
		partido.setVisitante(equipoRepository.getById(idVisitante));
		
		partidoRepository.save(partido);
	}

	public Collection<Partido> findByFecha(LocalDate fecha) {
		return partidoRepository.findByFecha(fecha);
	}

	public List<Integer> getJornadas() {
		List<Integer> jornadas = new ArrayList<>();
		List<Partido> partidos = partidoRepository.findAll();
		for ( Partido partido : partidos )  { 
			int nJornada = partido.getnJornada();
			if (!jornadas.contains(nJornada)) {
				jornadas.add(nJornada);
			}
		}
		return jornadas;
	}

	public boolean existeJornada(int nJornada) {
		boolean sol = false;
								//serian partidos
		for (Partido partido : partidoRepository.findAll()) {
			if (partido.getnJornada()==nJornada) {
				sol = true;
			}
		}
		return sol;
	}

	public Collection<Partido> findByJornada(int nJornada) {
		return partidoRepository.findBynJornadaOrderByFechaAsc(nJornada);
	}

}
