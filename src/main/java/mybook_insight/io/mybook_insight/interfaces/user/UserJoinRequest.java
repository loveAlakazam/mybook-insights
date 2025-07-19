package mybook_insight.io.mybook_insight.interfaces.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequest {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9_-]+$", message = "닉네임은 한글, 영문, 숫자, _, -만 사용 가능합니다.")
    private String nickname;

    public void validateBusinessRules() {
        validatePassword();
        validateNickName();
    }

    public void validatePassword() {
        // 연속된 같은 문자 체크
        if( hasConsecutiveSameChars(password) ) {
            throw new IllegalArgumentException("비밀번호에 같은 문자를 연속으로 사용할 수 없습니다.");
        }
    }

    public void validateNickName() {
        // 금지어 체크
        if( containsForbiddenWords(nickname) ) {
            throw new IllegalArgumentException("사용할 수 없는 닉네임 입니다.");
        }
        // 특수문자 조합 체크
        if( hasInvalidCharacterCombination(nickname) ) {
            throw new IllegalArgumentException("닉네임에 허용되지 않는 문자 조합이 있습니다.");
        }
    }

    private boolean hasConsecutiveSameChars(String password) {
        int MAX_CONSECUTIVE_LENGTH = 3;
        for( int i = 0; i <= password.length() - MAX_CONSECUTIVE_LENGTH; i++ ) {
            char firstChar = password.charAt(i);
            boolean allSame = true;
            for( int j = 0; j < MAX_CONSECUTIVE_LENGTH; j++ ) {
                if( password.charAt( i+j ) != firstChar ) {
                    allSame = false;
                    break;
                }
            }

            if(allSame) return true;
        }
        return false;
    }

    private boolean containsForbiddenWords(String nickname) {
        String[] forbiddenWords = {"admin", "관리자", "운영자", "mybook-insight"};
        String lowerNickname = nickname.toLowerCase();
        for(String word: forbiddenWords) {
            if(lowerNickname.contains(word)) return true;
        }
        return false;
    }
    private boolean hasInvalidCharacterCombination(String nickname) {
        return nickname.matches("^[_-]+$");
    }
}
