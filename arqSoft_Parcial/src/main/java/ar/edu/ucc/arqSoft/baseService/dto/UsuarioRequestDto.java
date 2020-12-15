package ar.edu.ucc.arqSoft.baseService.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class UsuarioRequestDto implements DtoEntity {

	private Long id;

	private String nombre;

	private String apellido;

	private String email;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
