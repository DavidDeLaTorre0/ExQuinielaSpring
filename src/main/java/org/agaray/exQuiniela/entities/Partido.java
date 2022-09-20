package org.agaray.exQuiniela.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Partido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int nJornada;
	private LocalDate fecha;
	private int gl;
	private int gv;
	
	@ManyToOne
	private Equipo local;
	@ManyToOne
	private Equipo visitante;
	
	//==================================
	public Partido() {
	}

	public Partido(int nJornada, LocalDate fecha, int gl, int gv, Equipo local, Equipo visitante) {
		super();
		this.nJornada = nJornada;
		this.fecha = fecha;
		this.gl = gl;
		this.gv = gv;
		this.local = local;
		this.visitante = visitante;
	}

	//==================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getnJornada() {
		return nJornada;
	}

	public void setnJornada(int nJornada) {
		this.nJornada = nJornada;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getGl() {
		return gl;
	}

	public void setGl(int gl) {
		this.gl = gl;
	}

	public int getGv() {
		return gv;
	}

	public void setGv(int gv) {
		this.gv = gv;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	//=============================
	
	public String getResultado() {
		String sol="X";
		if (this.gl > this.gv ) {
			sol = "1";
		}
		if (this.gv > this.gl ) {
			sol = "2";
		}
		return sol;
	}
	

	
	
}
