package ar.edu.ucc.arqSoft.baseService.dto;


import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class AsignarUsuarioTareaResponseDto implements DtoEntity{
	
	private Long id_usuario;
	
	private Long id_tarea;

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

	
	

}

	

