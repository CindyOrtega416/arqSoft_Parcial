package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.common.dao.GenericDao;

public interface EstadoDao extends GenericDao<Estado, Long> {
	

	public List<Estado> FindByName (String nombre);

}
