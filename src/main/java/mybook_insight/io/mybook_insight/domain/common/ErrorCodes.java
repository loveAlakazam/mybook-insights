package mybook_insight.io.mybook_insight.domain.common;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {
	/* User */
	// password
	INVALID_CONSECUTIVE_PASSWORD("비밀번호에 같은 문자를 연속으로 사용할 수 없습니다.", 400),
	PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.", 401),
	// email
	INVALID_EMAIL("유효하지 않은 이메일 형식입니다.", 400),
	EMAIL_ALREADY_EXISTS("이미 사용중인 이메일입니다.", 409),
	// nickname
	INVALID_NICKNAME("유효하지 않은 닉네임 형식입니다.", 400),
	FORBIDDEN_NICKNAME("금지된 단어가 포함된 닉네임 입니다.", 400),
	DUPLICATE_NICKNAME("이미 사용중인 닉네임 입니다.", 409),


	USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 404),
	NICKNAME_ALREADY_EXISTS("이미 사용중인 닉네임입니다.", 409),
/* Book */
/* Diary */
	/* Common */
	VALIDATION_ERROR("유효성 검사에 실패했습니다.", 400),
	LOGIN_REQUIRED("로그인이 필요합니다.", 401),
	UNAUTHORIZED("인증이 필요합니다.", 401),
	ACCESS_DENIED("접근이 거부되었습니다.", 403),
	RESOURCE_NOT_FOUND("요청한 리소스를 찾을 수 없습니다.", 404),
	CONFLICT("요청이 충돌을 일으켰습니다.", 409),
	UNSUPPORTED_MEDIA_TYPE("지원하지 않는 미디어 타입입니다.", 415),
	TOO_MANY_REQUESTS("요청이 너무 많습니다. 잠시 후 다시 시도해주세요.", 429),
	INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.", 500),
	UNEXPECTED_ERROR("예상치 못한 오류가 발생했습니다.", 500),
	DATABASE_ERROR("데이터베이스 오류가 발생했습니다.", 500),
	NOT_IMPLEMENTED("요청한 기능은 아직 구현되지 않았습니다.", 501),
	SERVICE_UNAVAILABLE("서비스를 사용할 수 없습니다.", 503),
	;


	private final String message;
	private final int statusCode;
	ErrorCodes(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public HttpStatus getHttpStatus() {
		return HttpStatus.valueOf(statusCode);
	}

}
