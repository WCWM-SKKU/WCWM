package wcwm.wcwm.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import wcwm.wcwm.domain.ExtracurricularActivity;

@Data
@AllArgsConstructor
public class ExtracurricularResponse {

    private Long id;

    private String title;

    private List<String> category;

    private List<String> target;

    private String host;

    private String sponsor;

    private String period;

    private String total_prize;

    private String url;

    private String poster;

    public ExtracurricularResponse(ExtracurricularActivity extracurricular) {
        this.id = extracurricular.getId();
        this.title = extracurricular.getTitle();
        this.category = extracurricular.categoryToList();
        this.target = extracurricular.targetToList();
        this.host = extracurricular.getHost();
        this.sponsor = extracurricular.getSponsor();
        this.period = extracurricular.getPeriod();
        this.total_prize = extracurricular.getTotal_prize();
        this.url = extracurricular.getUrl();
        this.poster = extracurricular.getPoster();
    }
}
