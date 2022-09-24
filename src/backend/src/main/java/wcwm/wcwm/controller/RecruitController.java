package wcwm.wcwm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.dto.response.DataResponse;
import wcwm.wcwm.dto.response.RecruitResponse;
import wcwm.wcwm.exception.CustomException;
import wcwm.wcwm.exception.CustomExceptionStatus;
import wcwm.wcwm.service.RecruitService;

@Slf4j
@RestController
@RequestMapping(value = "/recruit")
public class RecruitController {

    private final String[] arr = { "Server-Backend", "Frontend", "Web-FullStack", "Android", "iPhone", "ML",
            "Data-Engineer", "DBA", "Mobile-Game", "Game-Client", "Game-Server", "System-Network",
            "System-Software",
            "DevOps", "Internet-Security", "EmbeddedSW", "Robotics-Middleware", "QA", "IOT", "Application",
            "BlockChain" };

    private final RecruitService recruitService;
    private final List<String> dutyList = Arrays.asList(arr);

    public RecruitController(RecruitService recruitService) {

        this.recruitService = recruitService;
    }

    @GetMapping(params = { "min-career", "duty" })
    public DataResponse<List<RecruitResponse>> getRecruitsByDutyAndMinCareer(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "min-career") Integer minCareer,
            @RequestParam(value = "duty") String duty) {

        if (!dutyList.contains(duty))
            throw new CustomException(CustomExceptionStatus.NON_VALID_DUTY);

        log.info("GET /recruit?page={}&min-career={}&duty={}", page, minCareer, duty);
        return recruitService.findRecruitByMinCareerAndDuty(minCareer, duty, page);
    }

    @GetMapping(params = "min-career")
    public DataResponse<List<RecruitResponse>> getRecruitsByMinCareer(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "min-career") Integer minCareer) {

        log.info("GET /recruit?page={}&min-career={}", page, minCareer);
        return recruitService.findRecruitByMinCareer(minCareer, page);
    }

    @GetMapping(params = "duty")
    public DataResponse<List<RecruitResponse>> getRecruitsByDuty(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "duty") String duty) {

        if (!dutyList.contains(duty))
            throw new CustomException(CustomExceptionStatus.NON_VALID_DUTY);

        log.info("GET /recruit?page={}&duty={}", page, duty);
        return recruitService.findRecruitByDuty(duty, page);
    }

    @GetMapping
    public DataResponse<List<RecruitResponse>> getRecruits(
            @RequestParam(value = "page", defaultValue = "0") Integer page) {

        log.info("GET /recruit?page={}", page);
        return recruitService.findRecruits(page);
    }

    @GetMapping("/{recruitId}")
    public DataResponse<Optional<RecruitResponse>> getRecruit(@PathVariable Long recruitId) {
        log.info("GET /recruit/{}", recruitId);
        return recruitService.findOne(recruitId);
    }
}
