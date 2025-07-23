package mybook_insight.io.mybook_insight.domain.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.book.Book;
import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;
import mybook_insight.io.mybook_insight.domain.common.BusinessException;
import mybook_insight.io.mybook_insight.domain.common.ErrorCodes;
import mybook_insight.io.mybook_insight.domain.diary.Diary;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED )
@AllArgsConstructor( access = AccessLevel.PRIVATE )
@Builder(access = AccessLevel.PRIVATE)
@SQLRestriction( "deleted_at IS NULL")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "email", unique = true)
    private String email;

    @Column(name= "nickname", unique = true)
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

    public void withdraw() {
        this.softDelete();
    }

    public static User createForJoin(String email, String encodedPassword, String nickname) {
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .role(UserRole.GENERAL)
                .build();
    }

    public static User createForAdmin(String email, String encodedPassword, String nickname) {
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .role(UserRole.ADMIN)
                .build();
    }
}
