package ar.edu.ucc.arqSoft.baseService.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class TareaCambioEstadoRequestDto implements DtoEntity{


	private Long id_estado;
	
	private Long id_tarea;

	public Long getId_estado() {
		return id_estado;
	}

	public void setId_estado(Long id_estado) {
		this.id_estado = id_estado;
	}

	public Long getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(Long id_tarea) {
		this.id_tarea = id_tarea;
	}

	
	
	

}
