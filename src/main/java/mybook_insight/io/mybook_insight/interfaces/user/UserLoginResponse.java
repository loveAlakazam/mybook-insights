package mybook_insight.io.mybook_insight.interfaces.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mybook_insight.io.mybook_insight.domain.book.UserRole;
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

	// 정적 팩토리 메서드
	public static UserLoginResponse from(User user) {
		return UserLoginResponse.builder()
				.id(user.getId())
				.email(user.getEmail())
				.nickname(user.getNickname())
				.role(user.getRole())
				.createdAt(user.getCreatedAt())
				.build();
	}
}
