package org.agaray.exQuiniela.controller;

import org.agaray.exQuiniela.exception.DangerException;
import org.agaray.exQuiniela.exception.PRG;
import org.agaray.exQuiniela.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/equipo")
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@GetMapping("r")
	public String r(ModelMap m) {
		m.put("view", "equipo/r");
		m.put("equipos", equipoService.findAll());
		return "_t/frame";
	}

	@GetMapping("c")
	public String c(ModelMap m) {
		m.put("view", "equipo/c");
		return "_t/frame";
	}

	@PostMapping("c")
	public String cPost(
			@RequestParam(value = "nombre", required = false) String nombre
			) throws DangerException {
		try {
			if (nombre == null || nombre.equals("")) {
				throw new Exception("El nombre no puede ser nulo");
			}
			equipoService.save(nombre);
		} catch (Exception e) {
			PRG.error(e.getMessage(), "/equipo/c");
		}
		return "redirect:/equipo/r";
	}
}
