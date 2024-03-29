package wcwm.wcwm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.domain.ExtracurricularActivity;
import wcwm.wcwm.dto.response.DataResponse;
import wcwm.wcwm.dto.response.ExtracurricularResponse;
import wcwm.wcwm.exception.CustomException;
import wcwm.wcwm.repository.ExtracurricularRepository;

import static wcwm.wcwm.exception.CustomExceptionStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExtracurricularService {

        private final ExtracurricularRepository extracurricularRepository;
        private final ResponseService responseService;

        private final int MAX_RESULTS = 20;

        @Transactional
        public Long createExtracurricular(ExtracurricularActivity extracurricular) {
                // validate
                extracurricularRepository.save(extracurricular);
                return extracurricular.getId();
        }

        @Transactional(readOnly = true)
        public DataResponse<List<ExtracurricularResponse>> findExtracurriculars(Integer page) {
                List<ExtracurricularActivity> foundExtracurriculars = extracurricularRepository.findAll(
                                MAX_RESULTS * page,
                                MAX_RESULTS);
                return responseService.getDataResponse(toResponse(foundExtracurriculars));
        }

        @Transactional(readOnly = true)
        public DataResponse<ExtracurricularResponse> findOne(Long id) {
                ExtracurricularActivity find = extracurricularRepository.findById(id)
                                .orElseThrow(() -> new CustomException(NON_VALID_ID));

                ExtracurricularResponse result = new ExtracurricularResponse(find);
                return responseService.getDataResponse(result);
        }

        @Transactional(readOnly = true)
        public DataResponse<List<ExtracurricularResponse>> findExtracurricularByCategory(String category,
                        Integer page) {
                List<ExtracurricularActivity> foundExtracurriculars = extracurricularRepository.findByCategory(category,
                                MAX_RESULTS * page, MAX_RESULTS);
                return responseService.getDataResponse(toResponse(foundExtracurriculars));
        }

        public void delete(Long id) {
                ExtracurricularActivity extraCurri = extracurricularRepository.findById(id)
                        .orElseThrow(() -> new CustomException(NON_VALID_ID)); // Exception Type 생각해볼 것
                extracurricularRepository.delete(extraCurri.getId());
        }

        public List<ExtracurricularResponse> toResponse(List<ExtracurricularActivity> target) {
                return target.stream()
                                .map(ExtracurricularResponse::new)
                                .collect(Collectors.toList());
        }
}
