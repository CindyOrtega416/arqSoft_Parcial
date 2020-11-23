package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dao.GenericDaoImp;

@Repository
public class TareaImpDao extends GenericDaoImp<Tarea, Long> implements TareaDao {

	@Override
	public List<Tarea> FindByName (String nombre){

		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tarea> criteria = builder.createQuery(Tarea.class);
        Root<Tarea> entity = criteria.from(Tarea.class);

        criteria.select(entity).where(builder.equal(entity.get("nombre"+" "+"apellido"), nombre));
        return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public List<Tarea> findByTarea(String nombre_tarea){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tarea> criteria = builder.createQuery(Tarea.class);
		Root<Tarea> entity = criteria.from(Tarea.class);
		
		criteria.select(entity).where(builder.equal(entity.get("nombre_tarea"), nombre_tarea));
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public List<Tarea> findByProyecto(Proyecto proyecto){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tarea> criteria = builder.createQuery(Tarea.class);
		Root<Tarea> entity = criteria.from(Tarea.class);
		
		criteria.select(entity).where(builder.equal(entity.get("proyecto"), proyecto));
		return em.createQuery(criteria).getResultList();
	}
	
<<<<<<< HEAD
	@Override
	public Tarea createTarea(String nombre_tarea, String descripcion, Usuario usuario, Proyecto proyecto, Estado estado, Date fecha_inicio,  Date ultima_actualizacion) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tarea> criteria = builder.createQuery(Tarea.class);
		Root<Tarea> entity = criteria.from(Tarea.class);
		
	}		
	
	
	
}
=======
	
	
}
>>>>>>> dc5efa0aeab7a68445820f3390b02154b94dade3
