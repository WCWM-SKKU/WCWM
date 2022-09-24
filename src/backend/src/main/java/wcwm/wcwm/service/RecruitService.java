package wcwm.wcwm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.domain.Recruit;
import wcwm.wcwm.dto.response.RecruitResponse;
import wcwm.wcwm.exception.CustomException;
import wcwm.wcwm.repository.RecruitRepository;

import static wcwm.wcwm.exception.CustomExceptionStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecruitService {

    private final RecruitRepository recruitRepository;

    private final Integer MAX_RESULTS = 20;

    @Transactional(readOnly = true)
    public List<RecruitResponse> findRecruits(Integer page) {
        List<Recruit> foundRecruits = recruitRepository.findAll(MAX_RESULTS * page, MAX_RESULTS);
        return toResponse(foundRecruits);
    }

    @Transactional(readOnly = true)
    public Optional<RecruitResponse> findOne(Long id) {
        Recruit find = Optional.ofNullable(recruitRepository.findOne(id))
                .orElseThrow(() -> new CustomException(NON_VALID_ID));

        Optional<RecruitResponse> result = Optional
                .ofNullable(new RecruitResponse(SUCCESS.isSuccess(), SUCCESS.getCode(),
                        SUCCESS.getMessage(), find.getId(), find.getTitle(), find.getCompany(),
                        find.dutyToList(), find.getCareer(), find.getPeriod(), find.getLocation(), find.getUrl()));
        return result;
    }

    @Transactional(readOnly = true)
    public List<RecruitResponse> findRecruitByMinCareer(Integer minCareer, Integer page) {
        List<Recruit> foundRecruits = recruitRepository.findByMinCareer(minCareer, MAX_RESULTS * page, MAX_RESULTS);
        return toResponse(foundRecruits);
    }

    @Transactional(readOnly = true)
    public List<RecruitResponse> findRecruitByDuty(String duty, Integer page) {
        List<Recruit> foundRecruits = recruitRepository.findByDuty(duty, MAX_RESULTS * page, MAX_RESULTS);
        return toResponse(foundRecruits);
    }

    @Transactional(readOnly = true)
    public List<RecruitResponse> findRecruitByMinCareerAndDuty(Integer minCareer, String duty, Integer page) {
        List<Recruit> foundRecruits = recruitRepository.findByMinCareerAndDuty(minCareer, duty, MAX_RESULTS * page,
                MAX_RESULTS);
        return toResponse(foundRecruits);
    }

    public List<RecruitResponse> toResponse(List<Recruit> target) {
        return target.stream()
                .map(r -> new RecruitResponse(SUCCESS.isSuccess(), SUCCESS.getCode(),
                        SUCCESS.getMessage(), r.getId(), r.getTitle(), r.getCompany(), r.dutyToList(),
                        r.getCareer(), r.getPeriod(), r.getLocation(), r.getUrl()))
                .collect(Collectors.toList());
    }

}
