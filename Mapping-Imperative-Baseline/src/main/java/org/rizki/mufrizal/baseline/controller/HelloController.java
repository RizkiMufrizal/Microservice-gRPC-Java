package org.rizki.mufrizal.baseline.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.rizki.mufrizal.baseline.mapper.harmonized.GeneralHarmonizedResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.request.HelloServerRequest;
import org.rizki.mufrizal.baseline.mapper.object.server.response.GeneralServerResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.response.HelloServerResponse;
import org.rizki.mufrizal.baseline.restclient.HelloRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class HelloController {

    @Autowired
    private HelloRestClient helloRestClient;

    @Operation(summary = "Say Hello Function")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Say Hello Success Response",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HelloServerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralHarmonizedResponse.class)))})
    @PostMapping(value = "/hello")
    public ResponseEntity<?> sayHello(@RequestBody HelloServerRequest helloServerRequest) {
        GeneralServerResponse generalServerResponse = helloRestClient.sayHello(helloServerRequest);
        return new ResponseEntity<>(generalServerResponse.getBody(), HttpStatus.valueOf(generalServerResponse.getHttpStatus()));
    }

}