package ar.edu.ucc.arqSoft.baseService.dao;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dao.GenericDaoImp;

@Repository
public class UsuarioImpDao extends GenericDaoImp<Usuario, Long> implements UsuarioDao {

}
