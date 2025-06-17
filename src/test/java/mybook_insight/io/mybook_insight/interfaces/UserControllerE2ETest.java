package mybook_insight.io.mybook_insight.interfaces;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;

import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.domain.user.UserService;
import mybook_insight.io.mybook_insight.infrastructures.TestcontainersConfiguration;
import mybook_insight.io.mybook_insight.interfaces.user.UserController;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinRequest;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinResponse;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class UserControllerE2ETest {

	private static final Logger log = LoggerFactory.getLogger(UserControllerE2ETest.class);
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private UserController userController;
	@Autowired
	private UserService userService;

	@Test
	@DisplayName("일반 회원가입 성공")
	void join_success() {
		UserJoinRequest request = new UserJoinRequest("user1@email.com", "password1", "user1");
		ResponseEntity<UserJoinResponse> response = restTemplate.postForEntity(
			"/users/join",
			request,
			UserJoinResponse.class
		);


		log.info("Response: {}", response);
		log.info("Response-Body: {}", response.getBody());
		log.info("Response-Status: {}", response.getStatusCode());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("user1", response.getBody().getNickname());
		assertEquals(UserRole.GENERAL, response.getBody().getRole());
	}

	@Test
	void createAdmin_success() {
		UserJoinRequest request = new UserJoinRequest("admin1@email.com", "password2", "addmin1");
		ResponseEntity<UserJoinResponse> response = restTemplate.postForEntity(
			"/users/admin", request, UserJoinResponse.class);

		assert response.getStatusCode() == HttpStatus.CREATED;
		assert response.getBody() != null;
		assert response.getBody().getNickname().equals("addmin1");
		assert response.getBody().getRole().equals(UserRole.ADMIN);
	}
}