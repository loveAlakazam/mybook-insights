package mybook_insight.io.mybook_insight.infrastructure.persistence.user;

import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.user.User;
import mybook_insight.io.mybook_insight.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return false;
    }

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }
}
