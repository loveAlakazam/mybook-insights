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
public class UserJoinResponse {
    private Long id;
    private String email;
    private String nickname;
    private UserRole role;
    private LocalDateTime createdAt;


    // 정적팩터리 메서드
    public static UserJoinResponse from(User user) {
        return UserJoinResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
