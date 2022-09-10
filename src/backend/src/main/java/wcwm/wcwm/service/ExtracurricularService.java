package wcwm.wcwm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.domain.ExtracurricularActivity;
import wcwm.wcwm.repository.ExtracurricularRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExtracurricularService {

    private final ExtracurricularRepository extracurricularRepository;

    private final int MAX_RESULTS = 20;

    /**
     * Todo
     * 1) category: String to List and DTO 처리
     * ex) "웹/모바일/IT, 과학/공학" -> [”웹/모바일/IT”, “과학/공학"]
     * 
     * 2) querystring의 categoryId와 category 매칭
     */

    @Transactional(readOnly = true)
    public List<ExtracurricularActivity> findExtracurriculars(int page) {
        return extracurricularRepository.findAll(MAX_RESULTS * page + 1, MAX_RESULTS);
    }

    @Transactional(readOnly = true)
    public ExtracurricularActivity findExtracurricular(Long id) {
        return extracurricularRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<ExtracurricularActivity> findExtracurricularByCategory(String category, int page) {
        return extracurricularRepository.findByCategory(category, MAX_RESULTS * page + 1, MAX_RESULTS);
    }
}
