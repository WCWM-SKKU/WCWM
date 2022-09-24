package wcwm.wcwm.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitResponse {

    private Boolean isSuccess;

    private Integer code;

    private String message;

    private Long id;

    private String title;

    private String company;

    private List<String> duty;

    private String career;

    private String period;

    private String location;

    private String url;
}