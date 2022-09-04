package wcwm.wcwm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recruit")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Long id;

    private String title;

    private String company;

    private String duty;

    private String career;

    private Integer min_career;

    private String period;

    private String location;

    private String url;
}
