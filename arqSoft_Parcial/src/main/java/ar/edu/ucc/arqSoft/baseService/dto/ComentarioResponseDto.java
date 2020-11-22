package ar.edu.ucc.arqSoft.baseService.dto;

import java.util.Date;

import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class ComentarioResponseDto implements DtoEntity {

	
	private String nombre;
	private String descripcion;
	private Usuario usuario;
	private Tarea tarea;
	private Date fecha;
	
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
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
