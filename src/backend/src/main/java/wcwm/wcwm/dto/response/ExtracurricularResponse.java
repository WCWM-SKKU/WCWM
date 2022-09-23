package wcwm.wcwm.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}
