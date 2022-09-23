package wcwm.wcwm.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import wcwm.wcwm.dto.response.CommonResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public CommonResponse handleException(CustomException e, HttpServletRequest request) {

        CustomExceptionStatus status = e.getCustomExceptionStatus();
        log.error("errorCode : {}, url : {}, message : {}",
                status.getCode(), request.getRequestURI(), status.getMessage());

        return new CommonResponse(status.isSuccess(), status.getCode(), status.getMessage());
    }

}
