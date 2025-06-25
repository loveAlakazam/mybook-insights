package mybook_insight.io.mybook_insight.domain.common;

import org.springframework.http.HttpStatus;

/**
 * 클라이언트에서 잘못된 데이터가 전달되었을때 발생하는 에러이며,IllegalArgumentException을 상속받아 사용한다.
 */
public class InvalidDataValidationException extends IllegalArgumentException {
	private final ErrorCodes errorCode;

	public InvalidDataValidationException(ErrorCodes errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public InvalidDataValidationException(ErrorCodes errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}

	public InvalidDataValidationException(ErrorCodes errorCode, String additionalMessage) {
		super(errorCode.getMessage() + " " + additionalMessage);
		this.errorCode = errorCode;
	}

	public InvalidDataValidationException(ErrorCodes errorCode, String additionalMessage, Throwable cause) {
		super(errorCode.getMessage() + " " + additionalMessage, cause);
		this.errorCode = errorCode;
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public int getStatusCode() {
		return errorCode.getStatusCode();
	}

	public HttpStatus getHttpStatus() {
		return errorCode.getHttpStatus();
	}
}
