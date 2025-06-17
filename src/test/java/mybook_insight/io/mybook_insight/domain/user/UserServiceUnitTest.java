package mybook_insight.io.mybook_insight.domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.domain.common.BusinessException;
import mybook_insight.io.mybook_insight.domain.common.ErrorCodes;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinRequest;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinResponse;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder passwordEncoder;

	private final String SAMPLE_ENCODED_PASSWORD = "encodedSamplePassword";


	@Nested
	@DisplayName("일반회원 가입")
	class GeneralUserJoinTest {
		String email ="sample@mybook-insight.com";
		String rawPassword = "samplePassword";
		String nickname = "sampleNickname";
		private final UserJoinRequest validRequest = new UserJoinRequest(email, rawPassword, nickname);
		private final User mockGeneralUser = User.createForJoin(validRequest.getEmail(), SAMPLE_ENCODED_PASSWORD,
			validRequest.getNickname());

		@Test
		@DisplayName("일반 유저 생성에 성공한다")
		void joinGeneralUserSuccess() {
			// given
			given(userRepository.existsByEmail(validRequest.getEmail())).willReturn(false);
			given(userRepository.existsByNickname(validRequest.getNickname())).willReturn(false);
			given(passwordEncoder.encode(validRequest.getRawPassword())).willReturn(SAMPLE_ENCODED_PASSWORD);

			given(userRepository.save(any(User.class))).willReturn(mockGeneralUser);

			// when
			UserJoinResponse response = userService.join(validRequest);

			// then
			assertThat(response).isNotNull();
			assertThat(response.getEmail()).isEqualTo(validRequest.getEmail());
			assertThat(response.getNickname()).isEqualTo(validRequest.getNickname());
			assertThat(response.getRole()).isEqualTo(UserRole.GENERAL);

			// verify
			verify(userRepository).existsByEmail(validRequest.getEmail());
			verify(userRepository).existsByNickname(validRequest.getNickname());
			verify(passwordEncoder).encode(validRequest.getRawPassword());
			verify(userRepository).save(any(User.class));
		}
		@Test
		@DisplayName("실패: 이메일이 이미 존재하는 경우 예외가 발생한다")
		void joinGeneralUserFailure_EmailAlreadyExists() {
			// given
			given(userRepository.existsByEmail(anyString())).willReturn(true);
			// when & then
			assertThatThrownBy(() -> userService.join(validRequest)).
				isInstanceOf(BusinessException.class)
				.hasFieldOrPropertyWithValue("errorCode", ErrorCodes.EMAIL_ALREADY_EXISTS)
				.hasMessageContaining(ErrorCodes.EMAIL_ALREADY_EXISTS.getMessage());

			// verify
			verify(userRepository).existsByEmail(validRequest.getEmail());
			verify(userRepository, never()).existsByNickname(anyString());
			verify(passwordEncoder, never()).encode(anyString());
			verify(userRepository, never()).save(any(User.class));
		}
		@Test
		@DisplayName("실패: 닉네임이 이미 존재하는 경우 예외가 발생한다")
		void joinGeneralUserFailure_DuplicateNickname() {
			// given
			given(userRepository.existsByEmail(anyString())).willReturn(false);
			given(userRepository.existsByNickname(anyString())).willReturn(true);

			// when & then
			assertThatThrownBy(() -> userService.join(validRequest)).
				isInstanceOf(BusinessException.class)
				.hasFieldOrPropertyWithValue("errorCode", ErrorCodes.DUPLICATE_NICKNAME)
				.hasMessageContaining(ErrorCodes.DUPLICATE_NICKNAME.getMessage());

			// verify
			verify(userRepository).existsByEmail(validRequest.getEmail());
			verify(userRepository).existsByNickname(validRequest.getNickname());
			verify(passwordEncoder, never()).encode(anyString());
			verify(userRepository, never()).save(any(User.class));
		}
	}
	@Nested
	@DisplayName("관리자 회원 가입")
	class AdminUserJoinTest {
		String email ="sample@mybook-insight.com";
		String rawPassword = "samplePassword";
		String nickname = "sampleNickname";
		private final UserJoinRequest validRequest = new UserJoinRequest(email, rawPassword, nickname);
		private final User mockAdminUser = User.createForAdmin(validRequest.getEmail(), SAMPLE_ENCODED_PASSWORD,
			validRequest.getNickname());

		@Test
		@DisplayName("관리자 유저 생성에 성공한다")
		void joinAdminUserSuccess() {
			// given
			given(userRepository.existsByEmail(validRequest.getEmail())).willReturn(false);
			given(userRepository.existsByNickname(validRequest.getNickname())).willReturn(false);
			given(passwordEncoder.encode(validRequest.getRawPassword())).willReturn(SAMPLE_ENCODED_PASSWORD);
			given(userRepository.save(any(User.class))).willReturn(mockAdminUser);

			// when
			UserJoinResponse response = userService.join(validRequest, UserRole.ADMIN);

			// then
			assertThat(response).isNotNull();
			assertThat(response.getEmail()).isEqualTo(validRequest.getEmail());
			assertThat(response.getNickname()).isEqualTo(validRequest.getNickname());
			assertThat(response.getRole()).isEqualTo(UserRole.ADMIN);

			// verify
			verify(userRepository).existsByEmail(validRequest.getEmail());
			verify(userRepository).existsByNickname(validRequest.getNickname());
			verify(passwordEncoder).encode(validRequest.getRawPassword());
			verify(userRepository).save(any(User.class));
		}
	}


}
