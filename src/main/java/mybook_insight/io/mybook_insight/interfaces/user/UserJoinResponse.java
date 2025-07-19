package mybook_insight.io.mybook_insight.interfaces.user;

import lombok.Builder;
import lombok.Data;
import mybook_insight.io.mybook_insight.domain.book.UserRole;
import mybook_insight.io.mybook_insight.domain.user.User;

@Data
@Builder
public class UserJoinResponse {
    private Long id;
    private String email;
    private String nickname;
    private UserRole role;


    // 정적팩터리 메서드
    public static UserJoinResponse from(User user) {
        return UserJoinResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .role(user.getRole())
                .build();
    }

}
