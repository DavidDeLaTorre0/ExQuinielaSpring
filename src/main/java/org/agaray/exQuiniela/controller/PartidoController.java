package org.agaray.exQuiniela.controller;

import java.time.LocalDate;

import org.agaray.exQuiniela.exception.DangerException;
import org.agaray.exQuiniela.exception.PRG;
import org.agaray.exQuiniela.service.EquipoService;
import org.agaray.exQuiniela.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/partido")
public class PartidoController {

	@Autowired
	private PartidoService partidoService;

	@Autowired
	private EquipoService equipoService;

	@GetMapping("r")
	public String r(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "fecha", required = false) LocalDate fecha,
			ModelMap m) {

		m.put("view", "partido/r");
		if (fecha == null) {
			m.put("partidos", partidoService.findAll());
		} else {
			m.put("partidos", partidoService.findByFecha(fecha));
			m.put("fecha", fecha);
		}
		return "_t/frame";
	}

	@GetMapping("c")
	public String c(ModelMap m) {
		m.put("equipos", equipoService.findAll());
		m.put("view", "equipo/c");
		return "_t/frame";
	}

	@PostMapping("c")
	public String cPost(@RequestParam(value = "nJornada", required = false) int nJornada,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "fecha", required = false) LocalDate fecha,
			@RequestParam(value = "gl", required = false) int gl, @RequestParam(value = "gv", required = false) int gv,
			@RequestParam(value = "idLocal", required = false) Long idLocal,
			@RequestParam(value = "idVisitante", required = false) Long idVisitante) throws DangerException {
		try {
			if (fecha == null || idLocal == null || idVisitante == null) {
				throw new Exception("Los datos fecha, idLocal o idVisitante no pueden ser nulos");
			}
			if (gl < 0 || gv < 0) {
				throw new Exception("Los goles no pueden ser negativos");
			}
			if (nJornada < 1 || nJornada > 50) {
				throw new Exception("El número de jornada debe estar en el rango 1..50");
			}
			if (idLocal == idVisitante) {
				throw new Exception("No se permiten partidos contra uno mismo");
			}

			partidoService.save(nJornada, fecha, gl, gv, idLocal, idVisitante);

		} catch (Exception e) {
			PRG.error(e.getMessage(), "/partido/c");
		}
		return "redirect:/partido/r";
	}

	@GetMapping("quiniela")
	public String quiniela(ModelMap m) {
		m.put("jornadas", partidoService.getJornadas());
		m.put("view", "partido/quiniela");
		return "_t/frame";
	}

	@GetMapping("quinielaFinal")
	public String quinielaFinal(@RequestParam("nJornada") int nJornada, ModelMap m) throws DangerException {
		try {
			if (!partidoService.existeJornada(nJornada)) {
				throw new Exception("La jornada " + nJornada + " no existe");
			}
			m.put("nJornada", nJornada);
			m.put("partidos", partidoService.findByJornada(nJornada));
			m.put("view", "partido/quinielaFinal");
		} catch (Exception e) {
			PRG.error(e.getMessage(),"/partido/quiniela");
		}
		return "_t/frame";
	}

}
