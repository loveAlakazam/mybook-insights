package mybook_insight.io.mybook_insight.interfaces.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mybook_insight.io.mybook_insight.domain.common.ErrorCodes;
import mybook_insight.io.mybook_insight.domain.common.InvalidDataValidationException;

@Data
public class UserJoinRequest {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    private String rawPassword;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9_-]+$", message = "닉네임은 한글, 영문, 숫자, _, -만 사용 가능합니다.")
    private String nickname;

    @JsonCreator
    public UserJoinRequest(
        @JsonProperty("email") String email,
        @JsonProperty("password") String rawPassword,
        @JsonProperty("nickname") String nickname
    ) {
        this.email = email;
        this.rawPassword = rawPassword;
        this.nickname = nickname;

        validateBusinessRules();
    }

    public void validateBusinessRules() {
        validatePassword();
        validateNickName();
    }

    private void validatePassword() {
        // 연속된 같은 문자 체크
        if( hasConsecutiveSameChars(rawPassword) ) {
            throw new InvalidDataValidationException(ErrorCodes.INVALID_CONSECUTIVE_PASSWORD);
        }
    }

    private void validateNickName() {
        // 금지어 체크
        if( containsForbiddenWords(nickname) ) {
            throw new InvalidDataValidationException(ErrorCodes.FORBIDDEN_NICKNAME);
        }
        // 특수문자 조합 체크
        if( hasInvalidCharacterCombination(nickname) ) {
            throw new InvalidDataValidationException(ErrorCodes.INVALID_NICKNAME);
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
