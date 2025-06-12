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
public class UserJoinInfo {
    private Long id;
    private String email;
    private String nickname;
    private UserRole role;
    private LocalDateTime createdAt;


    // 정적팩터리 메서드
    public static UserJoinInfo from(User user) {
        return UserJoinInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
