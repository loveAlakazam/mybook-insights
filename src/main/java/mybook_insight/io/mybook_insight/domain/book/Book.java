package mybook_insight.io.mybook_insight.domain.book;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;
import mybook_insight.io.mybook_insight.domain.diary.Diary;
import mybook_insight.io.mybook_insight.domain.user.User;

@Entity
@Table(name = "book")
@Getter
@RequiredArgsConstructor
@SQLRestriction( "deleted_at IS NULL")
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "name", unique = true)
    private String name;

    @Column(name= "author", nullable = false)
    private String author;

    @Column(name= "publisher", nullable = false)
    private String publisher;

    @Column(name= "total_pages", nullable = false)
    private int totalPages;

    @Column(name= "detail_contents")
    private String detailContents;

    @Column(name= "thumbnail")
    private String thumbnail;

    // 관계 매핑: Book - User (N:1) - 등록한 관리자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_by", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Setter
    private User registeredBy;

    // 관계 매핑: Book - Diary (1:N)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();

}
