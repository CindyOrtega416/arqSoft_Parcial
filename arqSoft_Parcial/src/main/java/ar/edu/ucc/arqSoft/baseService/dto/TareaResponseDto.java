package ar.edu.ucc.arqSoft.baseService.dto;

import java.util.Date;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class TareaResponseDto implements DtoEntity {
	
	private String nombre;

	private Long id_usuario;
	
	private Long id_proyecto;
	
	private Date fecha;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	

}
