package store.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(FeignException.class)
    private ResponseEntity<ProblemDetail> handle(FeignException e) {

        HttpStatus code = HttpStatus.valueOf(e.status());
        String detail = e.getMessage();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(code, detail);
        return ResponseEntity.status(code).body(problemDetail);

    }
    
}
