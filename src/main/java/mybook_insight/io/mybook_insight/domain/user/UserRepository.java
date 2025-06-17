package mybook_insight.io.mybook_insight.domain.user;

public interface UserRepository {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    User save(User user);

}
