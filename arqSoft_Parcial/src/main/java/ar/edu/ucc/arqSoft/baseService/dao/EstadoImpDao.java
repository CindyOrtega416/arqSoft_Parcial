package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.common.dao.GenericDaoImp;

@Repository
public class EstadoImpDao extends GenericDaoImp<Estado, Long> implements EstadoDao {
	

	public List<Estado> FindByName (String nombre){

		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Estado> criteria = builder.createQuery(Estado.class);
        Root<Estado> entity = criteria.from(Estado.class);

        criteria.select(entity).where(builder.equal(entity.get("nombre"), nombre));
        return em.createQuery(criteria).getResultList();
	}

}
