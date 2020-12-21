package ar.edu.ucc.arqSoft.baseService.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table(name = "PROYECTO")
public class Proyecto extends GenericObject {

	@ManyToMany(mappedBy = "proyectos", fetch = FetchType.LAZY)
	private Set<Usuario> usuarios = new HashSet<>();

	@OneToMany(mappedBy = "proyectos", fetch = FetchType.LAZY)
	private Set<Tarea> tareas= new HashSet<>();

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NOMBRE")
	private String nombre;

	@NotNull
	@Size(min = 10, max = 500)
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@NotNull
	@Column(name = "FECHA_INICIO")
	private Date fecha_inicio;

	@NotNull
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fecha_actualizacion;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "ESTADO_PROYECTO")
	private EstadoProyecto estado;

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios.add(usuarios);
	}

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Tarea tareas) {
		this.tareas.add(tareas);
	}

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

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public EstadoProyecto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProyecto estado) {
		this.estado = estado;
	}

}