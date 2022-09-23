package wcwm.wcwm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {

    SUCCESS(true, 2000, "요청에 성공하였습니다."),

    // 4001 ~
    NON_VALID_ID(false, 4001, "유효하지 않은 id입니다."),
    NON_VALID_QUERY_PARAM(false, 4002, "유효하지 않은 Query Parameter입니다."),
    NON_VALID_MIN_CAREER(false, 4003, "유효하지 않은 경력입니다."),
    NON_VALID_DUTY(false, 4004, "유효하지 않은 직무입니다."),
    NON_VALID_CATEGORY(false, 4005, "유효하지 않은 카테고리입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
