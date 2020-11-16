package ar.edu.ucc.arqSoft.baseService.model;

import java.util.Set;

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
public class Usuario extends GenericObject{
	
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
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY)	//un usuario puede tener MUCHAS tareas
	private Set<Tarea> tareas;
	

	@ManyToMany
	 @JoinTable(
	   name="PROYECTO_USUARIO",
	   joinColumns=@JoinColumn(name="ID_USUARIO", referencedColumnName="ID"),
	   inverseJoinColumns=@JoinColumn(name="ID_PROYECTO", referencedColumnName="ID"))
	public Set<Proyecto> proyectos ;
	
	public Set<Proyecto> getProyectos(){return proyectos;}
	

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

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	
		


}