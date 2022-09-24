package wcwm.wcwm.service;

import org.springframework.stereotype.Service;

import wcwm.wcwm.dto.response.CommonResponse;
import wcwm.wcwm.dto.response.DataResponse;
import wcwm.wcwm.exception.CustomExceptionStatus;

@Service
public class ResponseService {
    public <T> DataResponse<T> getDataResponse(T data) {
        DataResponse<T> response = new DataResponse<>();
        response.setResult(data);
        response.setIsSuccess(true);
        response.setCode(2000);
        response.setMessage("요청에 성공하였습니다.");
        return response;
    }

    public CommonResponse getExceptionResponse(CustomExceptionStatus status) {
        CommonResponse response = new CommonResponse();
        response.setIsSuccess(status.isSuccess());
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());
        return response;
    }
}
