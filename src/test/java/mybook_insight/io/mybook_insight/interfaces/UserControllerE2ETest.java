package mybook_insight.io.mybook_insight.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.domain.user.User;
import mybook_insight.io.mybook_insight.domain.user.UserRepository;
import mybook_insight.io.mybook_insight.domain.user.UserService;
import mybook_insight.io.mybook_insight.infrastructures.TestcontainersConfiguration;
import mybook_insight.io.mybook_insight.interfaces.user.UserController;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinRequest;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinResponse;
import mybook_insight.io.mybook_insight.interfaces.user.UserLoginRequest;
import mybook_insight.io.mybook_insight.interfaces.user.UserLoginResponse;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerE2ETest {

	private static final Logger log = LoggerFactory.getLogger(UserControllerE2ETest.class);
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private UserController userController;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	String existedUserEmail = "user0@email.com";
	String existedUserRawPassword = "password0";
	String existedUserNickname = "user0";

	@BeforeEach
	void setUp() {
		// 테스트용 사용자 생성
		if(!userRepository.existsByEmail(existedUserEmail)) {
			String encodedPassword = passwordEncoder.encode(existedUserRawPassword);
			User user = User.createForJoin(
				existedUserEmail,
				encodedPassword,
				existedUserNickname
			);
			userRepository.save(user);
			log.info("테스트용 사용자 생성: {}", user.getEmail());
		}
	}

	@Test
	@DisplayName("일반 회원가입 성공")
	void join_success() {
		UserJoinRequest request = new UserJoinRequest("user1@email.com", "password1", "user1");
		ResponseEntity<UserJoinResponse> response = restTemplate.postForEntity(
			"/users/join",
			request,
			UserJoinResponse.class
		);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("user1", response.getBody().getNickname());
		assertEquals(UserRole.GENERAL, response.getBody().getRole());
	}

	@Test
	@DisplayName("관리자 회원가입 성공")
	void createAdmin_success() {
		UserJoinRequest request = new UserJoinRequest("admin1@email.com", "password2", "addmin1");
		ResponseEntity<UserJoinResponse> response = restTemplate.postForEntity(
			"/users/admin", request, UserJoinResponse.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("addmin1", response.getBody().getNickname());
		assertEquals(UserRole.ADMIN, response.getBody().getRole());
	}

	@Test
	@DisplayName("로그인 성공")
	void login_success() {
		// 로그인 요청
		UserLoginRequest loginRequest = new UserLoginRequest(
			"user0@email.com",
			"password0"
		);

		ResponseEntity<UserLoginResponse> response = restTemplate.postForEntity(
			"/users/login", loginRequest, UserLoginResponse.class);

		// 검증
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(existedUserEmail, response.getBody().getEmail());
		assertEquals(existedUserNickname, response.getBody().getNickname());
		assertEquals(UserRole.GENERAL, response.getBody().getRole());
	}
}