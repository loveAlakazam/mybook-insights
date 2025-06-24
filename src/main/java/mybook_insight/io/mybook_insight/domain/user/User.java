package mybook_insight.io.mybook_insight.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;

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




}
