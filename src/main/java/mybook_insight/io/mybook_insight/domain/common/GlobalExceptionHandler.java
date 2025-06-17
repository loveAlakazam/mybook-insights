package mybook_insight.io.mybook_insight.domain.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {
		log.error("BusinessException - code: {}, message: {}", e.getErrorCode(), e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(
			e.getErrorCode().name(),
			e.getErrorCode().getMessage(),
			request.getRequestURI()
		);
		return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
	}
	@ExceptionHandler(InvalidDataValidationException.class)
	public ResponseEntity<ErrorResponse> handleInvalidDataValidationException(InvalidDataValidationException e, HttpServletRequest request) {
		log.warn("InvalidDataValidationException - code: {}, message: {}", e.getErrorCode(), e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse(
			e.getErrorCode().name(),
			e.getErrorCode().getMessage(),
			request.getRequestURI()
		);
		return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
	}

	/**
	 * Spring Validation 어노테이션 검증 실패 처리
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		log.warn("Validation failed - message: {}", e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse(
			ErrorCodes.VALIDATION_ERROR.name(),
			ErrorCodes.VALIDATION_ERROR.getMessage(),
			request.getRequestURI()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	/**
	 * @PathVariable, @RequestParam 등에서 발생하는 바인딩 오류 처리
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
		log.warn("IllegalArgumentException - message: {}", e.getMessage(), e);
		ErrorResponse errorResponse = new ErrorResponse(
			e.getClass().getName(),
			e.getMessage(),
			request.getRequestURI()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
		log.error("Unexpected Exception - message: {}", e.getMessage(), e);
		ErrorResponse errorResponse = new ErrorResponse(
			ErrorCodes.UNEXPECTED_ERROR.name(),
			ErrorCodes.UNEXPECTED_ERROR.getMessage(),
			request.getRequestURI()
		);
		return ResponseEntity.status(ErrorCodes.UNEXPECTED_ERROR.getStatusCode()).body(errorResponse);
	}
}
