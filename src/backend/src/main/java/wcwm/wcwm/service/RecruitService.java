package wcwm.wcwm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.domain.Recruit;
import wcwm.wcwm.repository.RecruitRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecruitService {

    private final RecruitRepository recruitRepository;

    private final int MAX_RESULTS = 20;

    /**
     * Todo
     * duty: String to List and DTO 처리
     * ex) "프론트엔드, 웹 풀스텍" -> [”프론트엔드”, “웹 풀스택”]
     */
    @Transactional(readOnly = true)
    public List<Recruit> findRecruits(int page) {
        return recruitRepository.findAll(MAX_RESULTS * page + 1, MAX_RESULTS);
    }

    @Transactional(readOnly = true)
    public Recruit findOne(Long id) {
        return recruitRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Recruit> findRecruitByMinCareer(int minCareer, int page) {
        return recruitRepository.findByMinCareer(minCareer, MAX_RESULTS * page + 1, MAX_RESULTS);
    }

    @Transactional(readOnly = true)
    public List<Recruit> findRecruitByDuty(String duty, int page) {
        return recruitRepository.findByDuty(duty, MAX_RESULTS * page + 1, MAX_RESULTS);
    }

    @Transactional(readOnly = true)
    public List<Recruit> findRecruitByMinCareerAndDuty(int minCareer, String duty, int page) {
        return recruitRepository.findByMinCareerAndDuty(minCareer, duty, MAX_RESULTS * page + 1, MAX_RESULTS);
    }
}
