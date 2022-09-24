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

        @Transactional(readOnly = true)
        public DataResponse<List<ExtracurricularActivity>> findExtracurriculars(Integer page) {
                List<ExtracurricularActivity> foundExtracurriculars = extracurricularRepository.findAll(
                                MAX_RESULTS * page,
                                MAX_RESULTS);
                return responseService.getDataResponse(foundExtracurriculars);
        }

        @Transactional(readOnly = true)
        public DataResponse<Optional<ExtracurricularResponse>> findOne(Long id) {
                ExtracurricularActivity find = Optional.ofNullable(extracurricularRepository.findOne(id))
                                .orElseThrow(() -> new CustomException(NON_VALID_ID));

                Optional<ExtracurricularResponse> result = Optional
                                .ofNullable(new ExtracurricularResponse(find.getId(), find.getTitle(),
                                                find.categoryToList(), find.targetToList(),
                                                find.getHost(), find.getSponsor(), find.getPeriod(),
                                                find.getTotal_prize(), find.getUrl(),
                                                find.getPoster()));
                return responseService.getDataResponse(result);
        }

        @Transactional(readOnly = true)
        public DataResponse<List<ExtracurricularResponse>> findExtracurricularByCategory(String category,
                        Integer page) {
                List<ExtracurricularActivity> foundExtracurriculars = extracurricularRepository.findByCategory(category,
                                MAX_RESULTS * page, MAX_RESULTS);
                return responseService.getDataResponse(toResponse(foundExtracurriculars));
        }

        public List<ExtracurricularResponse> toResponse(List<ExtracurricularActivity> target) {
                return target.stream()
                                .map(e -> new ExtracurricularResponse(e.getId(), e.getTitle(), e.categoryToList(),
                                                e.targetToList(),
                                                e.getHost(), e.getSponsor(), e.getPeriod(), e.getTotal_prize(),
                                                e.getUrl(), e.getPoster()))
                                .collect(Collectors.toList());
        }
}
