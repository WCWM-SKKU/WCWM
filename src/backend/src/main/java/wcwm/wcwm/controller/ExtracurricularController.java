package wcwm.wcwm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.dto.ExtracurricularResponse;
import wcwm.wcwm.service.ExtracurricularService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/extracurricular")
public class ExtracurricularController {

    private final ExtracurricularService extracurricularService;

    /*
     * Todo
     * Category - Id 매칭
     */

    @GetMapping(params = "category")
    public List<ExtracurricularResponse> getExtracurricularsByCategory(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "category") String category) {

        log.info("GET /extracurricular?page={}&category={}", page, category);
        return extracurricularService.findExtracurricularByCategory(category, page);
    }

    @GetMapping
    public List<ExtracurricularResponse> getExtracurriculars(
            @RequestParam(value = "page", defaultValue = "0") Integer page) {

        log.info("GET /extracurricular?page={}", page);
        return extracurricularService.findExtracurriculars(page);
    }

    @GetMapping("/{extracurricularId}")
    public ExtracurricularResponse getExtracurricular(@PathVariable Long extracurricularId) {
        log.info("GET /extracurricular/{}", extracurricularId);
        return extracurricularService.findOne(extracurricularId);
    }
}
