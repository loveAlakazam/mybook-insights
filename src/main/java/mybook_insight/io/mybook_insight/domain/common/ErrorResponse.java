package mybook_insight.io.mybook_insight.domain.common;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final String code;
	private final String message;
	private final LocalDateTime timestamp;
	private final String path;

	public ErrorResponse(String code, String message, String path) {
		this.code = code;
		this.message = message;
		this.timestamp = LocalDateTime.now();
		this.path = path;
	}
}
