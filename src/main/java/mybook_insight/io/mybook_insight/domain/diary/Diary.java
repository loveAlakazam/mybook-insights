package mybook_insight.io.mybook_insight.domain.diary;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mybook_insight.io.mybook_insight.domain.book.Book;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;
import mybook_insight.io.mybook_insight.domain.user.User;

@Entity
@Table(name = "diary")
@Getter @RequiredArgsConstructor
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
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;

    // 관계 매핑: Diary - Book (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    @Setter
    private Book book;


}
