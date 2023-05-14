package wcwm.wcwm.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "extracurricular")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class ExtracurricularActivity extends BaseTimeEntity {

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

    private String url;

    private String poster;

    /*
     * category, target: String to List
     * ex) "웹/모바일/IT, 과학/공학" -> [”웹/모바일/IT”, “과학/공학"]
     * ex) "일반인, 대학생, 청소년, 기타" -> ["일반인", "대학생", "청소년", "기타"],
     */
    public List<String> categoryToList() {
        return Arrays.asList(this.category.split(","));
    }

    public List<String> targetToList() {
        return Arrays.asList(this.target.split(","));
    }
}
