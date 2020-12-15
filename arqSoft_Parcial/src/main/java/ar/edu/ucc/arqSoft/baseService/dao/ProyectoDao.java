package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.common.dao.GenericDao;

public interface ProyectoDao extends GenericDao<Proyecto, Long> {

	public List<Proyecto> FindByName(String nombre);

}
