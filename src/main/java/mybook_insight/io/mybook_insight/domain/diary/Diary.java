package mybook_insight.io.mybook_insight.domain.diary;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mybook_insight.io.mybook_insight.domain.book.Book;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;
import mybook_insight.io.mybook_insight.domain.user.User;

@Entity
@Table(name = "diary")
@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED )
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "title",  nullable = false)
    private String title;

    @Column(name= "thoughts",  nullable = false)
    private String thoughts;

    @Column(name= "is_finished", nullable = false)
    private boolean isFinished = false;

    @Column(name = "read_pages")
    private int readPages;

    // 관계 매핑: Diary - User (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Setter
    private User user;

    // 관계 매핑: Diary - Book (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Setter
    private Book book;


    public static Diary of(String title, String thoughts, boolean isFinished, int readPages) {
        return Diary.builder()
            .title(title)
            .thoughts(thoughts)
            .isFinished(isFinished)
            .readPages(readPages)
            .build();
    }
    public static Diary of(String title, String thoughts, boolean isFinished) {
        return Diary.builder()
            .title(title)
            .thoughts(thoughts)
            .isFinished(isFinished)
            .build();
    }
}
