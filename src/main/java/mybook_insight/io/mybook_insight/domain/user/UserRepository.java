package mybook_insight.io.mybook_insight.domain.user;

import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    User save(User user);
    User findByEmail(String email);
}
