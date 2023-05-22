package wcwm.wcwm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wcwm.wcwm.domain.ExtracurricularActivity;
import wcwm.wcwm.dto.response.DataResponse;
import wcwm.wcwm.dto.response.ExtracurricularResponse;
import wcwm.wcwm.exception.CustomException;
import wcwm.wcwm.exception.CustomExceptionStatus;
import wcwm.wcwm.repository.ExtracurricularRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExtracurricularServiceTest {

    @InjectMocks
    private ExtracurricularService extracurricularService;

    @Mock
    private ExtracurricularRepository extracurricularRepository;

    @Mock
    private ResponseService responseService;

    private ExtracurricularActivity extracurricular;

    @BeforeEach
    public void setUp() {
        extracurricular = new ExtracurricularActivity();
        extracurricular.setId(1L);
        extracurricular.setTitle("Title");
        extracurricular.setCategory("Category");
        extracurricular.setTarget("Target");
        extracurricular.setHost("Host");
        extracurricular.setSponsor("Sponsor");
        extracurricular.setPeriod("Period");
        extracurricular.setTotal_prize("Total_prize");
        extracurricular.setUrl("Url");
        extracurricular.setPoster("Poster");
    }

    @Test
    @DisplayName("Test findOne")
    public void testFindOne() {
        // given
        given(extracurricularRepository.findById(1L)).willReturn(Optional.of(extracurricular));

        ExtracurricularResponse extracurricularResponse = new ExtracurricularResponse(extracurricular);
        DataResponse<ExtracurricularResponse> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(extracurricularResponse);
        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(extracurricularResponse)).willReturn(expectedResponse);

        // when
        DataResponse<ExtracurricularResponse> actualResponse = extracurricularService.findOne(1L);

        // then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test findOne: 결과가 없을 경우")
    public void testFindOneNonExistent() {
        // given
        given(extracurricularRepository.findById(1L)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () -> extracurricularService.findOne(1L));
        assertEquals(CustomExceptionStatus.NON_VALID_ID, exception.getCustomExceptionStatus());
    }

    @Test
    @DisplayName("Test findExtracurriculars")
    public void testFindExtracurriculars() {
        // given
        List<ExtracurricularActivity> extracurriculars = Collections.singletonList(extracurricular);
        given(extracurricularRepository.findAll(0, 20)).willReturn(extracurriculars);

        DataResponse<List<ExtracurricularResponse>> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(extracurricularService.toResponse(extracurriculars));
        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(extracurricularService.toResponse(extracurriculars))).willReturn(expectedResponse);

        // when
        DataResponse<List<ExtracurricularResponse>> actualResponse = extracurricularService.findExtracurriculars(0);

        // then
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void testFindExtracurricularByCategory() {
        // given
        String category = "웹/모바일/IT";
        List<ExtracurricularActivity> extracurriculars = Collections.singletonList(extracurricular);
        given(extracurricularRepository.findByCategory(category, 0, 20)).willReturn(extracurriculars);

        List<ExtracurricularResponse> extracurricularResponses = Collections.singletonList(new ExtracurricularResponse(extracurricular));
        DataResponse<List<ExtracurricularResponse>> expectedResponse = new DataResponse<>();
        expectedResponse.setResult(extracurricularResponses);
        expectedResponse.setIsSuccess(true);
        expectedResponse.setCode(2000);
        expectedResponse.setMessage("요청에 성공하였습니다.");

        given(responseService.getDataResponse(extracurricularResponses)).willReturn(expectedResponse);

        // when
        DataResponse<List<ExtracurricularResponse>> actualResponse = extracurricularService.findExtracurricularByCategory(category, 0);

        // then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testDelete() {
        // given
        given(extracurricularRepository.findById(1L)).willReturn(Optional.of(extracurricular));

        // when
        assertDoesNotThrow(() -> extracurricularService.delete(1L));

        // then
        verify(extracurricularRepository, times(1)).delete(extracurricular.getId());
    }
}