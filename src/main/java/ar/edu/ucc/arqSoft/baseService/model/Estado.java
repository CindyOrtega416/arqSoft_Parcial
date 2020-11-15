package ar.edu.ucc.arqSoft.baseService.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.edu.ucc.arqSoft.common.model.GenericObject;

public class Estado extends GenericObject{
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "NOMBRE")
	private String nombre;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "FECHA_INICIO")
	private Date fecha_inicio;
	
	@NotNull
	@Size(min = 1, max = 250)
	@Column(name = "ULTIMA_ACTUALIZACION")
	private Date ultima_actualizacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_TAREA")
	private Tarea tarea;

}
