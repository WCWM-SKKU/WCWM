package wcwm.wcwm.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import wcwm.wcwm.domain.Recruit;

@Data
@AllArgsConstructor
public class RecruitResponse {

    private Long id;

    private String title;

    private String company;

    private List<String> duty;

    private String career;

    private String period;

    private String location;

    private String url;

    public RecruitResponse(Recruit recruit) {
        this.id = recruit.getId();
        this.title = recruit.getTitle();
        this.company = recruit.getCompany();
        this.duty = recruit.dutyToList();
        this.career = recruit.getCareer();
        this.period = recruit.getCareer();
        this.location = recruit.getLocation();
        this.url = recruit.getUrl();
    }

}