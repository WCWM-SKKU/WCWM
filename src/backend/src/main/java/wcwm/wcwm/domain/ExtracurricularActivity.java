package wcwm.wcwm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class ExtracurricularActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extracurricular_id")
    private Long id;

    private String title;

    private String category;

    private String target;

    private String host;

    private String sponsor;

    private String period;

    private String total_prize;

    private String homepage;

    private String poster;
}
