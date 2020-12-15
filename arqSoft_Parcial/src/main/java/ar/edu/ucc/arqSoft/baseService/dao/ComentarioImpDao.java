package ar.edu.ucc.arqSoft.baseService.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.common.dao.GenericDaoImp;

@Repository
public class ComentarioImpDao extends GenericDaoImp<Comentario, Long> implements ComentarioDao {
	@Override
	public List<Comentario> getComentariosTarea(Long ID_TAREA) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Comentario> criteria = builder.createQuery(Comentario.class);
        Root<Comentario> entity = criteria.from(Comentario.class);

        criteria.select(entity).where(builder.equal((entity.get("tarea").get("id")), ID_TAREA));

        return em.createQuery(criteria).getResultList();
	}
}
