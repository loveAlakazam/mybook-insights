package mybook_insight.io.mybook_insight.infrastructure.persistence.user;

import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;
}
