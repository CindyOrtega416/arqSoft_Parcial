package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;
import java.util.Date;

import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dao.GenericDao;

public interface TareaDao extends GenericDao<Tarea, Long> {
	
	public List<Tarea> FindByName (String nombre);
	public List<Tarea> findByTarea(String nombre_tarea);
	public List<Tarea> findByProyecto(Proyecto proyecto);
	public Tarea createTarea(String nombre_tarea, String descripcion, Usuario usuario, Proyecto proyecto, Estado estado, Date fecha_inicio,  Date ultima_actualizacion);

}