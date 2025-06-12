package mybook_insight.io.mybook_insight.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.common.BusinessException;
import mybook_insight.io.mybook_insight.domain.common.ErrorCodes;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinRequest;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinResponse;
import mybook_insight.io.mybook_insight.interfaces.user.UserLoginRequest;
import mybook_insight.io.mybook_insight.interfaces.user.UserLoginResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserJoinResponse join(UserJoinRequest request) {
        return join(request, UserRole.GENERAL);
    }

    @Transactional
    public UserJoinResponse join(UserJoinRequest request, UserRole userRole) {
		// email 중복 체크
		if( userRepository.existsByEmail(request.getEmail()) ) {
			throw new BusinessException(ErrorCodes.EMAIL_ALREADY_EXISTS);
		}

		// 닉네임 중복체크
		if(userRepository.existsByNickname(request.getNickname())) {
			throw new BusinessException(ErrorCodes.DUPLICATE_NICKNAME);
		}
		// 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(request.getRawPassword());

		// User 엔티티 생성
		User user;
		if(userRole == UserRole.ADMIN) {
			// 관리자 계정 생성
			user = User.createForAdmin(request.getEmail(), encodedPassword, request.getNickname());
		} else {
			// 일반 사용자 계정 생성
			user = User.createForJoin(request.getEmail(),  encodedPassword, request.getNickname());
		}

		// 데이터베이스 저장
		User savedUser = userRepository.save(user);
		log.info("{}회원 가입완료: userId: {}, email: {}", savedUser.getRole(), savedUser.getId(), savedUser.getEmail());

		// 응답 DTO 반환
		return UserJoinResponse.from(savedUser);

	}

    @Transactional
    public UserLoginInfo login(UserLoginCommand command) {
		// 이메일로 사용자 조회
		User user = userRepository.findByEmail(command.getEmail());
		if(user == null) {
			throw new BusinessException(ErrorCodes.USER_NOT_FOUND);
		}

		// 비밀번호 검증
		if (!passwordEncoder.matches(command.getRawPassword(), user.getPassword())) {
			throw new BusinessException(ErrorCodes.PASSWORD_MISMATCH);
		}

		return UserLoginInfo.of(user);
	}

    @Transactional
    public void getProfile() {}

    @Transactional
    public void updatePassword() {}

    @Transactional
    public void updateNickname() {}

    @Transactional
    public void withdraw() {}

}
