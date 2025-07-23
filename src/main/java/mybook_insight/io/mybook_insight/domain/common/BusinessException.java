package mybook_insight.io.mybook_insight.domain.common;

import lombok.Getter;

/* 비즈니스로직에서의 예외는 RuntimeException 으로부터 상속 받아서 사용한다. */
public class BusinessException extends RuntimeException {
	private final ErrorCodes errorCode;
	public BusinessException(ErrorCodes errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
	public BusinessException(ErrorCodes errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}
	public BusinessException(ErrorCodes errorCode, String additionalMessage) {
		super(errorCode.getMessage() + " " + additionalMessage);
		this.errorCode = errorCode;
	}
	public BusinessException(ErrorCodes errorCode, String additionalMessage, Throwable cause) {
		super(errorCode.getMessage() + " " + additionalMessage, cause);
		this.errorCode = errorCode;
	}
	public ErrorCodes getErrorCode() {
		return errorCode;
	}
	public int getStatusCode() {
		return errorCode.getStatusCode();
	}
	public String getMessage() {
		return errorCode.getMessage();
	}
}
