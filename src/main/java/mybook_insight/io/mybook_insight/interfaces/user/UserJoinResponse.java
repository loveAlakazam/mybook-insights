package mybook_insight.io.mybook_insight.interfaces.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mybook_insight.io.mybook_insight.domain.user.UserJoinInfo;
import mybook_insight.io.mybook_insight.domain.user.UserRole;
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
    public static UserJoinResponse from(UserJoinInfo userJoinInfo) {
        return UserJoinResponse.builder()
                .id(userJoinInfo.getId())
                .email(userJoinInfo.getEmail())
                .nickname(userJoinInfo.getNickname())
                .role(userJoinInfo.getRole())
                .createdAt(userJoinInfo.getCreatedAt())
                .build();
    }

}
