package wcwm.wcwm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.dto.response.RecruitResponse;
import wcwm.wcwm.service.RecruitService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recruit")
public class RecruitController {

    private final RecruitService recruitService;

    @GetMapping(params = { "min-career", "duty" })
    public List<RecruitResponse> getRecruitsByDutyAndMinCareer(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "min-career") Integer minCareer,
            @RequestParam(value = "duty") String duty) {

        log.info("GET /recruit?page={}&min-career={}&duty={}", page, minCareer, duty);
        return recruitService.findRecruitByMinCareerAndDuty(minCareer, duty, page);
    }

    @GetMapping(params = "min-career")
    public List<RecruitResponse> getRecruitsByMinCareer(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "min-career") Integer minCareer) {

        log.info("GET /recruit?page={}&min-career={}", page, minCareer);
        return recruitService.findRecruitByMinCareer(minCareer, page);
    }

    @GetMapping(params = "duty")
    public List<RecruitResponse> getRecruitsByDuty(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "duty") String duty) {

        log.info("GET /recruit?page={}&duty={}", page, duty);
        return recruitService.findRecruitByDuty(duty, page);
    }

    @GetMapping
    public List<RecruitResponse> getRecruits(
            @RequestParam(value = "page", defaultValue = "0") Integer page) {

        log.info("GET /recruit?page={}", page);
        return recruitService.findRecruits(page);
    }

    @GetMapping("/{recruitId}")
    public RecruitResponse getRecruit(@PathVariable Long recruitId) {
        log.info("GET /recruit/{}", recruitId);
        return recruitService.findOne(recruitId);
    }
}
