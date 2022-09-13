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

        /**
         * Todo
         * Optional 처리
         */

        public List<Recruit> findAll(Integer firstResult, Integer maxResults) {
                return em.createQuery("select r from Recruit r order by r.id desc", Recruit.class)
                                .setFirstResult(firstResult)
                                .setMaxResults(maxResults)
                                .getResultList();
        }

        public Recruit findOne(Long id) {
                return em.find(Recruit.class, id);
        }

        public List<Recruit> findByMinCareer(Integer minCareer, Integer firstResult, Integer maxResults) {
                return em
                                .createQuery("select r from Recruit r where r.min_career <= :minCareer and r.max_career >= :minCareer order by r.id desc",
                                                Recruit.class)
                                .setParameter("minCareer", minCareer)
                                .setFirstResult(firstResult)
                                .setMaxResults(maxResults)
                                .getResultList();
        }

        public List<Recruit> findByDuty(String duty, Integer firstResult, Integer maxResults) {
                return em.createQuery("select r from Recruit r where r.duty like :duty order by r.id desc",
                                Recruit.class)
                                .setParameter("duty", "%" + duty + "%")
                                .setFirstResult(firstResult)
                                .setMaxResults(maxResults)
                                .getResultList();
        }

        public List<Recruit> findByMinCareerAndDuty(Integer minCareer, String duty, Integer firstResult,
                        Integer maxResults) {
                return em
                                .createQuery(
                                                "select r from Recruit r where r.min_career <= :minCareer and r.max_career >= :minCareer and r.duty like :duty order by r.id desc",
                                                Recruit.class)
                                .setParameter("minCareer", minCareer)
                                .setParameter("duty", "%" + duty + "%")
                                .setFirstResult(firstResult)
                                .setMaxResults(maxResults)
                                .getResultList();
        }
}
