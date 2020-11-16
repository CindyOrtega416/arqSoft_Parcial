package ar.edu.ucc.arqSoft.baseService.dto;

import java.util.Date;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class ComentarioRequestDto implements DtoEntity {
	
	private String nombre;
	private String descripcion;
	private Long id_usuario;
	private Long id_tarea;
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
	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Long getId_tarea() {
		return id_tarea;
	}
	public void setId_tarea(Long id_tarea) {
		this.id_tarea = id_tarea;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
