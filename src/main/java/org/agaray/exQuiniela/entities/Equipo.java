package org.agaray.exQuiniela.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	@OneToMany(mappedBy = "local")
	private Collection<Partido> local;
	@OneToMany(mappedBy = "visitante")
	private Collection<Partido> visitante;
	
	//==================================
	
	public Equipo() {
		this.local = new ArrayList<>();
		this.visitante = new ArrayList<>();
	}

	public Equipo(String nombre) {
		super();
		this.local = new ArrayList<>();
		this.visitante = new ArrayList<>();
		this.nombre = nombre;
	}

	//==================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Partido> getLocal() {
		return local;
	}

	public void setLocal(Collection<Partido> local) {
		this.local = local;
	}

	public Collection<Partido> getVisitante() {
		return visitante;
	}

	public void setVisitante(Collection<Partido> visitante) {
		this.visitante = visitante;
	}

	
	
}
