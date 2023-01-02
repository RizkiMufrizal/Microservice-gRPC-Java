package org.rizki.mufrizal.baseline.exception;

import org.rizki.mufrizal.baseline.helper.LoggingHelper;
import org.rizki.mufrizal.baseline.mapper.harmonized.GeneralHarmonizedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> generalException(Exception ex) {
        LoggingHelper.log(ex);

        GeneralHarmonizedResponse generalHarmonizedResponse = GeneralHarmonizedResponse.builder()
                .code("500")
                .description("General Error")
                .build();

        return new ResponseEntity<>(generalHarmonizedResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
