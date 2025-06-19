package mybook_insight.io.mybook_insight.infrastructures;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import mybook_insight.io.mybook_insight.domain.user.User;
import mybook_insight.io.mybook_insight.infrastructure.persistence.user.UserJpaRepository;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
public class UserJpaRepositoryTest {
	@Autowired
	private UserJpaRepository userJpaRepository;

	@Test
	void findByEmail_excludesSoftDeletedUser() {
		// given
		User user = User.createForJoin("test@email.com", "password", "nickname");
		userJpaRepository.save(user);

		// when: soft delete
		user.softDelete();
		userJpaRepository.save(user);

		// then: 조회되지 않아야 함
		Optional<User> result = userJpaRepository.findByEmail("test@email.com");
		assertThat(result).isEmpty();
	}
}
