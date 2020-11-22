package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dao.GenericDao;

public interface UsuarioDao extends GenericDao<Usuario, Long>{
	
	public List<Usuario> FindByName (String nombre);

}
