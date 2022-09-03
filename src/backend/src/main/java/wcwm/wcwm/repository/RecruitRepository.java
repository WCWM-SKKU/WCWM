package wcwm.wcwm.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import wcwm.wcwm.domain.Recruit;

@Repository
@RequiredArgsConstructor
public class RecruitRepository {

    private final EntityManager em;

    List<Recruit> findAll(int firstResult, int maxResults) {
        return em.createQuery("select r from Recruit r", Recruit.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    Recruit findById(Long id) {
        return em.find(Recruit.class, id);
    }

    List<Recruit> findByMinCareer(int minCareer, int firstResult, int maxResults) {
        return em.createQuery("select r from Recruit r where r.min_career >= :minCareer", Recruit.class)
                .setParameter("minCareer", minCareer)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    List<Recruit> findByDuty(String duty, int firstResult, int maxResults) {
        return em.createQuery("select r from Recruit r where r.duty like %:duty%", Recruit.class)
                .setParameter("duty", duty)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    List<Recruit> findByMinCareerAndDuty(int minCareer, String duty, int firstResult, int maxResults) {
        return em
                .createQuery("select r from Recruit r where r.min_career >= :minCareer and r.duty like %:duty%",
                        Recruit.class)
                .setParameter("minCareer", minCareer)
                .setParameter("duty", duty)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
}
