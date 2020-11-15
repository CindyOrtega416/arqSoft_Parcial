package ar.edu.ucc.arqSoft.baseService.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

public class Tarea extends GenericObject {
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NOMBRE_TAREA")
	private String nombre_tarea;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)		//Una tarea en particular tiene solo un usuario asignado
	@JoinColumn(name="ID_USUARIO")			//MUCHAS tareas pueden ser asignadas a UN usuario
	private Usuario usuario;
	
	@NotNull
	@Column(name = "FECHA_INICIO")
	private Date fecha_inicio;
	
	@NotNull
	@Column(name = "ULTIMA_ACTUALIZACION")
	private Date ultima_actualizacion;
	
	@OneToMany(mappedBy="tarea", fetch = FetchType.LAZY)
	private Set<Estado> estado;
	
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "PRIORIDAD")
	private Prioridad prioridad;
	
	@OneToMany(mappedBy="tarea", fetch = FetchType.LAZY)
	private Set<Comentario> comentario;
	
	

}
