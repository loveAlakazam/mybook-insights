package mybook_insight.io.mybook_insight.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinRequest;
import mybook_insight.io.mybook_insight.interfaces.user.UserJoinResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Transactional
    public UserJoinResponse join(UserJoinRequest request) {
        return join(request, UserRole.GENERAL);
    }

    @Transactional
    public UserJoinResponse join(UserJoinRequest request, UserRole userRole) {
        // email 중복 체크
        if( userRepository.existsByEmail(request.getEmail()) ) {
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }

        // 닉네임 중복체크
        // 비밀번호 암호화
        // User 엔티티 생성
        // 데이터베이스 저장
        // 응답 DTO 반환
    }

    @Transactional
    public void login() {}

    @Transactional
    public void getProfile() {}

    @Transactional
    public void updatePassword() {}

    @Transactional
    public void updateInformation() {}

    @Transactional
    public void withdraw() {}

}
