package ar.edu.ucc.arqSoft.baseService.dao;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.common.dao.GenericDaoImp;

@Repository
public class ComentarioImpDao extends GenericDaoImp<Comentario, Long> implements ComentarioDao {

}
