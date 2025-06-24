package mybook_insight.io.mybook_insight.domain.diary;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;

@Entity
@Table(name = "diary")
@Getter @RequiredArgsConstructor
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "title",  nullable = false)
    private String title;

    @Column(name= "is_finished", nullable = false)
    private boolean isFinished = false;

    @Column(name = "read_pages")
    private int readPages;

}
