package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;

import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.common.dao.GenericDao;

public interface ComentarioDao extends GenericDao<Comentario, Long> {
	public List<Comentario> getComentariosTarea(Long ID_TAREA);
}
