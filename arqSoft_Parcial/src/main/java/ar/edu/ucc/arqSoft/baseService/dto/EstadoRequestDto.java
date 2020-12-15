package ar.edu.ucc.arqSoft.baseService.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class EstadoRequestDto implements DtoEntity {

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
