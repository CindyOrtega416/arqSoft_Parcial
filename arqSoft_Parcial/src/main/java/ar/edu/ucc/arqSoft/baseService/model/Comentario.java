package ar.edu.ucc.arqSoft.baseService.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table(name = "COMENTARIO")
public class Comentario extends GenericObject {

	@NotNull
	@Size(min = 1, max = 500)
	@Column(name = "NOMBRE")
	private String nombre;

	@NotNull
	@Size(min = 1, max = 500)
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TAREA")
	private Tarea tarea;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@NotNull
	@Size(min = 1, max = 12)
	@Column(name = "FECHA")
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

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}