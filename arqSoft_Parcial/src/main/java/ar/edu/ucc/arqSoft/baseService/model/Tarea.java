package ar.edu.ucc.arqSoft.baseService.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;


@Entity
@Table(name = "TAREA")
public class Tarea extends GenericObject {
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NOMBRE")
	private String nombre;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@ManyToMany(mappedBy = "Usuarios")
	private Set<Usuario> usuarios;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="ID_USUARIO")
	//private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PROYECTO")
	private Proyecto proyecto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ESTADO")
	private Estado estado;
	
	@NotNull
	@Size(min = 1, max = 12)
	@Column(name = "FECHA_INICIO")
	private Date fecha_inicio;
	
	@NotNull
	@Size(min = 1, max = 12)
	@Column(name = "ULTIMA_ACTUALIZACION")
	private Date ultima_actualizacion;
	
	
	@OneToMany (targetEntity=Usuario.class, mappedBy="usuario", fetch = FetchType.LAZY)
	private Set<Comentario> comentarios;

	public String getNombre() {
		return nombre;
	}

	public void setNombre_tarea(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getUltima_actualizacion() {
		return ultima_actualizacion;
	}

	public void setUltima_actualizacion(Date ultima_actualizacion) {
		this.ultima_actualizacion = ultima_actualizacion;
	}



	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios.add(usuarios);
	}
	


	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	
	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}


	
	
	
