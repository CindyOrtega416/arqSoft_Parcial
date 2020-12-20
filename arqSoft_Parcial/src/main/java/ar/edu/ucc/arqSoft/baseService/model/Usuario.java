package ar.edu.ucc.arqSoft.baseService.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

@Entity
@Table(name = "USUARIO")
public class Usuario extends GenericObject {

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NOMBRE")
	private String nombre;

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "APELLIDO")
	private String apellido;

	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "EMAIL")
	private String email;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "USUARIO_TAREA", joinColumns = {@JoinColumn(name = "ID_USUARIO")}, inverseJoinColumns = {@JoinColumn(name = "ID_TAREA")})
	Set<Tarea> tareas = new HashSet<>();

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private Set<Comentario> comentarios;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "USUARIO_PROYECTO", joinColumns =  {@JoinColumn(name = "ID_USUARIO")}, inverseJoinColumns = {@JoinColumn(name = "ID_PROYECTO")})
	Set<Proyecto> proyectos = new HashSet<>();
	

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Tarea tareas) {
		this.tareas.add(tareas);
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Comentario comentarios) {
		this.comentarios.add(comentarios);
	}

	public Set<Proyecto> getProyectos() {
		return proyectos;

	}

	public void setProyectos(Proyecto proyectos) {
		this.proyectos.add(proyectos);
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