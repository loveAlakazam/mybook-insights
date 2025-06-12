package mybook_insight.io.mybook_insight.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginCommand {
	private String email;
	private String rawPassword;


	private UserLoginCommand(
		String email,
		String rawPassword
	) {
		this.email = email;
		this.rawPassword = rawPassword;
	}

	public static UserLoginCommand of(
		String email,
		String rawPassword
	) {
		return new UserLoginCommand(email, rawPassword);
	}
}
