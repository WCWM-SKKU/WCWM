package wcwm.wcwm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.domain.ExtracurricularActivity;
import wcwm.wcwm.dto.response.DataResponse;
import wcwm.wcwm.dto.response.ExtracurricularResponse;
import wcwm.wcwm.exception.CustomException;
import wcwm.wcwm.service.ExtracurricularService;

import static wcwm.wcwm.exception.CustomExceptionStatus.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/extracurricular")
public class ExtracurricularController {

    private final ExtracurricularService extracurricularService;
    private final Map<Integer, String> categoryMappingMap;

    public ExtracurricularController(ExtracurricularService extracurricularService) {

        this.extracurricularService = extracurricularService;
        this.categoryMappingMap = new HashMap<>();

        initCategoryMappingMap();

    }

    private void initCategoryMappingMap() {
        categoryMappingMap.put(1, "기획/아이디어");
        categoryMappingMap.put(2, "광고/마케팅");
        categoryMappingMap.put(3, "논문/리포트");
        categoryMappingMap.put(4, "영상/UCC/사진");
        categoryMappingMap.put(5, "디자인/캐릭터/웹툰");
        categoryMappingMap.put(6, "웹/모바일/IT");
        categoryMappingMap.put(7, "게임/소프트웨어");
        categoryMappingMap.put(8, "과학/공학");
        categoryMappingMap.put(9, "문학/글/시나리오");
        categoryMappingMap.put(10, "건축/건설/인테리어");
        categoryMappingMap.put(12, "네이밍/슬로건");
        categoryMappingMap.put(12, "예체능/미술/음악");
        categoryMappingMap.put(13, "대외활동/서포터즈");
        categoryMappingMap.put(14, "봉사활동");
        categoryMappingMap.put(15, "취업/창업");
        categoryMappingMap.put(16, "해외");
        categoryMappingMap.put(17, "기타");
    }

    @GetMapping(params = "categoryId")
    public DataResponse<List<ExtracurricularResponse>> getExtracurricularsByCategory(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "categoryId") Integer categoryId,
            HttpServletRequest request) {

        if (categoryId < 1 || categoryId > 17)
            throw new CustomException(NON_VALID_CATEGORY);

        String category = categoryMappingMap.get(categoryId);
        log.info("GET /extracurricular?page={}&categoryId={}=>{}", page, categoryId, category);
        return extracurricularService.findExtracurricularByCategory(category, page);
    }

    @GetMapping
    public DataResponse<List<ExtracurricularActivity>> getExtracurriculars(
            @RequestParam(value = "page", defaultValue = "0") Integer page) {

        log.info("GET /extracurricular?page={}", page);
        return extracurricularService.findExtracurriculars(page);
    }

    @GetMapping("/{extracurricularId}")
    public DataResponse<ExtracurricularResponse> getExtracurricular(@PathVariable Long extracurricularId) {
        log.info("GET /extracurricular/{}", extracurricularId);
        return extracurricularService.findOne(extracurricularId);
    }
}
