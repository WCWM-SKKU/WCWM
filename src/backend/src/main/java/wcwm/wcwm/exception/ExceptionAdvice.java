package wcwm.wcwm.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.dto.response.CommonResponse;
import wcwm.wcwm.service.ResponseService;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(CustomException.class)
    public CommonResponse handleException(CustomException e, HttpServletRequest request) {

        CustomExceptionStatus status = e.getCustomExceptionStatus();
        log.error("errorCode : {}, url : {}, message : {}",
                status.getCode(), request.getRequestURI(), status.getMessage());

        return responseService.getExceptionResponse(status);
    }

}
