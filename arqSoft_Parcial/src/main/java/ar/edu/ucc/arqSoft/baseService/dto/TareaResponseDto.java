package ar.edu.ucc.arqSoft.baseService.dto;

import java.util.Date;
import java.util.Set;

import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class TareaResponseDto implements DtoEntity {
	
	private String nombre_tarea;

	private String descripcion;
	
	private Set<Usuario> usuario;
	
	private Set<Comentario> comentario;
	
	private Proyecto proyecto;
	
	private Set<ComentarioResponseDto> comentarios;
	
	private Estado estado;




	public String getNombre_tarea() {
		return nombre_tarea;
	}


	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}




	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Set<ComentarioResponseDto> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<ComentarioResponseDto> comentarios) {
		this.comentarios = comentarios;
	}
	
	public void addComentarios(Comentario comentario)
	{
		ComentarioResponseDto comentarioDto=new ComentarioResponseDto();
		comentarioDto.setDescripcion(comentario.getDescripcion());
		comentarioDto.setNombre(comentario.getNombre());
		comentarioDto.setTarea(comentario.getTarea());
		comentarioDto.setUsuario(comentario.getUsuario());
		this.comentarios.add(comentarioDto);
	}

	
	

}
