package mybook_insight.io.mybook_insight.interfaces.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mybook_insight.io.mybook_insight.domain.user.UserLoginInfo;
import mybook_insight.io.mybook_insight.domain.user.UserRole;
import mybook_insight.io.mybook_insight.domain.user.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
	private Long id;
	private String email;
	private String nickname;
	private UserRole role;
	private LocalDateTime createdAt;

	private String accessToken = "access-token"; // 액세스 토큰
	private String refreshToken= "refresh-token"; // 리프래시 토큰

	// 정적 팩토리 메서드
	public static UserLoginResponse of(User user) {
		return UserLoginResponse.builder()
				.id(user.getId())
				.email(user.getEmail())
				.nickname(user.getNickname())
				.role(user.getRole())
				.createdAt(user.getCreatedAt())
				.accessToken("access-token") // 실제 토큰 생성 로직 필요
				.refreshToken("refresh-token") // 실제 토큰 생성 로직 필요
				.build();
	}

	public static UserLoginResponse of(UserLoginInfo info) {
		return UserLoginResponse.builder()
				.id(info.getId())
				.email(info.getEmail())
				.nickname(info.getNickname())
				.role(info.getRole())
				.createdAt(info.getCreatedAt())
				.accessToken("access-token") // 실제 토큰 생성 로직 필요
				.refreshToken("refresh-token") // 실제 토큰 생성 로직 필요
				.build();
	}
}
