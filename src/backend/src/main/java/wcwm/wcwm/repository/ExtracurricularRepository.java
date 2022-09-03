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

    List<ExtracurricularActivity> findAll(int firstResult, int maxResults) {
        return em.createQuery("select e from ExtracurricularActivity e", ExtracurricularActivity.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    ExtracurricularActivity findById(Long id) {
        return em.find(ExtracurricularActivity.class, id);
    }

    List<ExtracurricularActivity> findByCategory(String category, int firstResult, int maxResults) {
        return em
                .createQuery("select e from ExtracurricularActivity e where e.category like %:category%",
                        ExtracurricularActivity.class)
                .setParameter("category", category)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
}
