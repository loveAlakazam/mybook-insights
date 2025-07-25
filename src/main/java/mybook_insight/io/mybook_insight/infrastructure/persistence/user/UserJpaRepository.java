package mybook_insight.io.mybook_insight.infrastructure.persistence.user;

import java.util.Optional;

import mybook_insight.io.mybook_insight.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
