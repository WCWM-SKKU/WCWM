package wcwm.wcwm.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import wcwm.wcwm.domain.ExtracurricularActivity;

@Repository
@RequiredArgsConstructor
public class ExtracurricularRepository {

    private final EntityManager em;

    /**
     * Todo
     * Optional 처리
     */

    public List<ExtracurricularActivity> findAll(Integer firstResult, Integer maxResults) {
        return em.createQuery("select e from ExtracurricularActivity e", ExtracurricularActivity.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    public ExtracurricularActivity findOne(Long id) {
        return em.find(ExtracurricularActivity.class, id);
    }

    public List<ExtracurricularActivity> findByCategory(String category, Integer firstResult, Integer maxResults) {
        return em
                .createQuery("select e from ExtracurricularActivity e where e.category like %:category%",
                        ExtracurricularActivity.class)
                .setParameter("category", category)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
}
