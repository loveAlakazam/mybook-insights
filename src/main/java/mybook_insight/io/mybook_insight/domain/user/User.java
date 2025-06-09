package mybook_insight.io.mybook_insight.domain.user;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.book.Book;
import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;
import mybook_insight.io.mybook_insight.domain.diary.Diary;

@Entity
@Table(name = "user")
@Getter @RequiredArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "email", unique = true)
    private String email;

    @Column(name= "nickname", nullable = false)
    private String nickname;

    @Column(name= "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name= "role", nullable = false)
    private UserRole role = UserRole.GENERAL;

    // 관계 매핑: User - Diary (1:N)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();

    // 관계 매핑: User - Book (1:N) - 관리자만 책등록 가능함
    @OneToMany(mappedBy = "registeredBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> registeredBooks = new ArrayList<>();

}
