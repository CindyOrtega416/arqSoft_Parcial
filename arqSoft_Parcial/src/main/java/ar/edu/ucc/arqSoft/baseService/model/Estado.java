package ar.edu.ucc.arqSoft.baseService.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

public class Estado extends GenericObject{
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NOMBRE")
	private String nombre;
	
	@NotNull
	@Size(min = 1, max = 500)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@OneToMany(mappedBy="estado", fetch = FetchType.LAZY)
	private Set<Tarea> tareas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
}
