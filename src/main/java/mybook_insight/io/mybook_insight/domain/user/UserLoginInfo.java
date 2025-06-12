package mybook_insight.io.mybook_insight.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInfo {
	private Long id;
	private String email;
	private String nickname;
	private UserRole role;
	private LocalDateTime createdAt;


	// 정적 팩토리 메서드
	public static UserLoginInfo of(User user) {
		return UserLoginInfo.builder()
				.id(user.getId())
				.email(user.getEmail())
				.nickname(user.getNickname())
				.role(user.getRole())
				.createdAt(user.getCreatedAt())
				.build();
	}
}
