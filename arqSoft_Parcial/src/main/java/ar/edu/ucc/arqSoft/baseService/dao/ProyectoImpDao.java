package ar.edu.ucc.arqSoft.baseService.dao;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.common.dao.GenericDaoImp;

@Repository
public class ProyectoImpDao extends GenericDaoImp<Proyecto, Long> implements ProyectoDao{

}
