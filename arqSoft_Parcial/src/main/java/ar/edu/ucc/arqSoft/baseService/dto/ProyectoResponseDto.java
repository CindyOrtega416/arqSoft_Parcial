package ar.edu.ucc.arqSoft.baseService.dto;

import java.util.Date;
import java.util.Set;

import ar.edu.ucc.arqSoft.baseService.model.EstadoProyecto;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class ProyectoResponseDto implements DtoEntity {

	
	private String nombre;
	
	private String descripcion;
	
	private Date fecha_inicio;
	
	private Date fecha_actualizacion;
	
	private EstadoProyecto estado;
	
	private Set<Tarea> tarea;
	
	private Set<Usuario> usuario;

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

	public EstadoProyecto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProyecto estado) {
		this.estado = estado;
	}

	public Set<Tarea> getTarea() {
		return tarea;
	}

	public void setTarea(Set<Tarea> tarea) {
		this.tarea = tarea;
	}

	public Set<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}
	
	
	

}
