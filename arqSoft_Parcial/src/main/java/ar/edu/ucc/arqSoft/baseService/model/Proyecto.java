package ar.edu.ucc.arqSoft.baseService.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table(name = "PROYECTO")
public class Proyecto extends GenericObject {

	@ManyToMany(mappedBy = "proyectos")
	private Set<Usuario> usuarios;

	public Set<Usuario> getUsuarios() {return usuarios;};			//esto esta bien?
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NOMBRE")
	private String nombre;
	
	@NotNull
	@Size(min = 10, max = 500)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@NotNull
	@Column(name = "FECHA_INICIO")
	private Date fecha_inicio;
	
	@NotNull
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fecha_actualizacion;

	@NotNull
	@Column(name = "ESTADO") 
	private String estado;			//asi se puede relacionar con estado?

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

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	
	
}