package mybook_insight.io.mybook_insight.interfaces.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.user.UserJoinCommand;
import mybook_insight.io.mybook_insight.domain.user.UserLoginCommand;
import mybook_insight.io.mybook_insight.domain.user.UserRole;
import mybook_insight.io.mybook_insight.domain.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/join")
	public ResponseEntity<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest request) {
		UserJoinResponse response = UserJoinResponse.from(
			userService.join(
				UserJoinCommand.of( request.getEmail(), request.getPassword(), request.getNickname() )
			)
	 	);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PostMapping("/admin")
	public ResponseEntity<UserJoinResponse> createAdmin(@Valid @RequestBody UserJoinRequest request) {
		UserJoinResponse response = UserJoinResponse.from(
			userService.join(
				UserJoinCommand.of( request.getEmail(), request.getPassword(), request.getNickname() ),
				UserRole.ADMIN
		   	)
	 	);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
		UserLoginResponse response = UserLoginResponse.from(
			userService.login( UserLoginCommand.of( request.getEmail(), request.getRawPassword()) )
	 	);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
