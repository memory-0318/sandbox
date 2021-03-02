package tw.brian;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.brian.model.dto.ResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description: 攔截ConstraintViolationException與MethodArgumentValidException並客製化錯誤訊息
 * @date: 2021/2/8
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDTO<String>> handleConstraintViolationException(
        HttpServletRequest req,
        ConstraintViolationException e) {
        List<String> constraintsErrorMessage = e.getConstraintViolations()
            .stream()
            .map(fieldError -> String
                .format("%s - %s", fieldError.getPropertyPath().toString(), fieldError.getMessage()))
            .collect(Collectors.toList());
        String errorMessageForOutput = Joiner.on("; ").join(constraintsErrorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ResponseDTO.<String>createSuccessBuilder()
                .setMsg(errorMessageForOutput)
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<String>> handleMethodArgumentNotValidException(
        HttpServletRequest req,
        MethodArgumentNotValidException e) {
        List<String> fieldErrorMessages = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> String.format("%s - %s", fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());
        String errorMessageForOutput = Joiner.on("; ").join(fieldErrorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ResponseDTO.<String>createSuccessBuilder()
                .setMsg(errorMessageForOutput)
                .build());
    }
}
