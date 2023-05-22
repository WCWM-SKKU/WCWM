package wcwm.wcwm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wcwm.wcwm.domain.Recruit;
import wcwm.wcwm.dto.response.DataResponse;
import wcwm.wcwm.dto.response.RecruitResponse;
import wcwm.wcwm.exception.CustomException;
import wcwm.wcwm.exception.CustomExceptionStatus;
import wcwm.wcwm.repository.RecruitRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RecruitServiceTest {

    @InjectMocks
    private RecruitService recruitService;

    @Mock
    private RecruitRepository recruitRepository;

    @Mock
    private ResponseService responseService;

    private Recruit recruit;

    @BeforeEach
    public void setUp() {
        recruit = new Recruit();
        recruit.setId(1L);
        recruit.setTitle("Title");
        recruit.setCompany("Company");
        recruit.setDuty("Server-Backend");
        recruit.setCareer("2~3년");
        recruit.setMin_career(2);
        recruit.setMax_career(3);
        recruit.setPeriod("2023.01.01 ~ 2023.12.31");
        recruit.setLocation("Location");
        recruit.setUrl("Url");
    }

    @Test
    @DisplayName("Test findOne")
    void testFindOne() {
        // given
        given(recruitRepository.findById(1L)).willReturn(java.util.Optional.of(recruit));

        RecruitResponse recruitResponse = new RecruitResponse(recruit);
        DataResponse<RecruitResponse> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(recruitResponse);

        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(recruitResponse)).willReturn(expectedResponse);

        // when
        DataResponse<RecruitResponse> actualResponse = recruitService.findOne(1L);

        // then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test findOne: 결과가 없을 경우")
    public void testFindOneNonExistent() {
        // given
        given(recruitRepository.findById(1L)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () -> recruitService.findOne(1L));
        assertEquals(CustomExceptionStatus.NON_VALID_ID, exception.getCustomExceptionStatus());
    }

    @Test
    @DisplayName("Test findRecruits")
    void testFindRecruits() {
        // given
        List<Recruit> recruits = Collections.singletonList(recruit);
        given(recruitRepository.findAll(0, 20)).willReturn(recruits);

        DataResponse<List<RecruitResponse>> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(recruitService.toResponse(recruits));
        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(recruitService.toResponse(recruits))).willReturn(expectedResponse);

        // when
        DataResponse<List<RecruitResponse>> actualResponse = recruitService.findRecruits(0);

        // then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test findRecruitsByMinCareer")
    void testFindRecruitByMinCareer() {
        // given
        Integer minCareer = 2;
        List<Recruit> recruits = Collections.singletonList(recruit);
        given(recruitRepository.findByMinCareer(minCareer, 0, 20)).willReturn(recruits);

        List<RecruitResponse> recruitResponses = Collections.singletonList(new RecruitResponse(recruit));
        DataResponse<List<RecruitResponse>> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(recruitResponses);
        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(recruitResponses)).willReturn(expectedResponse);

        // when
        DataResponse<List<RecruitResponse>> actualResponse = recruitService.findRecruitByMinCareer(minCareer, 0);

        // then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test findRecruitsByDuty")
    void testFindRecruitByDuty() {
        // given
        String duty = "Server-Backend";
        List<Recruit> recruits = Collections.singletonList(recruit);
        given(recruitRepository.findByDuty(duty, 0, 20)).willReturn(recruits);

        List<RecruitResponse> recruitResponses = Collections.singletonList(new RecruitResponse(recruit));
        DataResponse<List<RecruitResponse>> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(recruitResponses);
        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(recruitResponses)).willReturn(expectedResponse);

        // when
        DataResponse<List<RecruitResponse>> actualResponse = recruitService.findRecruitByDuty(duty, 0);

        // then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test findRecruitsByMinCareerAndDuty")
    void testFindRecruitByMinCareerAndDuty() {
        // given
        Integer minCareer = 2;
        String duty = "Server-Backend";
        List<Recruit> recruits = Collections.singletonList(recruit);
        given(recruitRepository.findByMinCareerAndDuty(minCareer,duty, 0, 20)).willReturn(recruits);

        List<RecruitResponse> recruitResponses = Collections.singletonList(new RecruitResponse(recruit));
        DataResponse<List<RecruitResponse>> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(recruitResponses);
        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(recruitResponses)).willReturn(expectedResponse);

        // when
        DataResponse<List<RecruitResponse>> actualResponse = recruitService.findRecruitByMinCareerAndDuty(minCareer, duty, 0);

        // then
        assertEquals(expectedResponse, actualResponse);
    }
}