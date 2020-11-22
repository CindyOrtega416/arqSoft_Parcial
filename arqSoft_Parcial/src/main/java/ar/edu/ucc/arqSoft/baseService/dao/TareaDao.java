package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.common.dao.GenericDao;

public interface TareaDao extends GenericDao<Tarea, Long> {
	
	public List<Tarea> FindByName (String nombre);

}
