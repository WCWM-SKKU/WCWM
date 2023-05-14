package wcwm.wcwm.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import wcwm.wcwm.domain.ExtracurricularActivity;

@Repository
@RequiredArgsConstructor
public class ExtracurricularRepository {

    private final EntityManager em;

    public void save(ExtracurricularActivity extracurricularActivity) {
        em.persist(extracurricularActivity);
    }

    public List<ExtracurricularActivity> findAll(Integer firstResult, Integer maxResults) {
        return em
                .createQuery("select e from ExtracurricularActivity e order by e.id desc",
                        ExtracurricularActivity.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    public Optional<ExtracurricularActivity> findById(Long id) {
        return Optional.ofNullable(em.find(ExtracurricularActivity.class, id));
    }

    public List<ExtracurricularActivity> findByCategory(String category, Integer firstResult, Integer maxResults) {
        return em
                .createQuery(
                        "select e from ExtracurricularActivity e where e.category like :category order by e.id desc",
                        ExtracurricularActivity.class)
                .setParameter("category", "%" + category + "%")
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    public void delete(Long id) {
        em.remove(em.find(ExtracurricularActivity.class, id));
    }
}
