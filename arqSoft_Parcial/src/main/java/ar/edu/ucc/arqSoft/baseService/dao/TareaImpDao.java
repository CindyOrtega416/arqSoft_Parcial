package ar.edu.ucc.arqSoft.baseService.dao;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.common.dao.GenericDaoImp;

@Repository
public class TareaImpDao extends GenericDaoImp<Tarea, Long> implements TareaDao {

}