package org.rizki.mufrizal.baseline.exception;

import org.rizki.mufrizal.baseline.helper.LoggingHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> generalException(Exception ex) {
        LoggingHelper.log(ex);

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("code", "500");
        stringObjectMap.put("description", "General Error");

        return new ResponseEntity<>(stringObjectMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
