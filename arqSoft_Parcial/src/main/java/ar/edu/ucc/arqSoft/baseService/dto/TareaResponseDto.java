package ar.edu.ucc.arqSoft.baseService.dto;

import java.util.Date;
import java.util.Set;

import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class TareaResponseDto implements DtoEntity {
	
	private String nombre;

	private String description;
	
	private Set<Usuario> usuario;
	
	private Set<Comentario> comentario;
	
	private Proyecto proyecto;
	

	private Estado estado;


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Usuario> getUsuario() {
		return usuario;
	}


	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}


	public Set<Comentario> getComentario() {
		return comentario;
	}


	public void setComentario(Set<Comentario> comentario) {
		this.comentario = comentario;
	}


	public Proyecto getProyecto() {
		return proyecto;
	}


	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	

	
	

}
