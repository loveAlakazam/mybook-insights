package mybook_insight.io.mybook_insight.interfaces.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginRequest {
	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	@NotBlank(message = "비밀번호는 필수입니다.")
	private String rawPassword;

	@JsonCreator
	public UserLoginRequest(
		@JsonProperty("email") String email,
		@JsonProperty("password") String rawPassword
	) {
		this.email = email;
		this.rawPassword = rawPassword;
	}
}
