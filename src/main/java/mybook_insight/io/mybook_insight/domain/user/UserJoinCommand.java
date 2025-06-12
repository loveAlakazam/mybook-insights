package mybook_insight.io.mybook_insight.domain.user;


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
public class UserJoinCommand {
    private String email;
    private String rawPassword;
    private String nickname;


    public UserJoinCommand( String email, String rawPassword, String nickname ) {
        this.email = email;
        this.rawPassword = rawPassword;
        this.nickname = nickname;

    }

    public static UserJoinCommand of( String email, String rawPassword, String nickname ) {
        return new UserJoinCommand(email, rawPassword, nickname);
    }

}
