package org.agaray.exQuiniela.service;

import java.util.Collection;

import org.agaray.exQuiniela.entities.Equipo;
import org.agaray.exQuiniela.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {
	
	@Autowired
	private EquipoRepository equipoRepository;
	
	//Recojo todos los equipos
	public Collection<Equipo> findAll() {
		return equipoRepository.findAll();
	}

	public void save(String nombre) throws Exception {
		//el try es or si me intenta meter el mismo nombre , por que en la entidad equipo habiamos puesto el nombre como unique
				//si va mal el catch recoge la excepcion y redirige
		try {
			equipoRepository.save(new Equipo(nombre));
		}
		catch (Exception e) {
			throw new Exception("El equipo "+nombre+" ya existe");
		}
	}
}
