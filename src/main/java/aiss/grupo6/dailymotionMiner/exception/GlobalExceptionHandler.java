package aiss.grupo6.dailymotionMiner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChannelNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleChannelNotFoundException(ChannelNotFoundException ex) {
        return new ResponseEntity<>(ex.getBody(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalErrorException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleInternalErrorException(InternalErrorException ex) {
        return new ResponseEntity<>(ex.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}