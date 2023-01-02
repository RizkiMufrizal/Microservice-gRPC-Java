package org.rizki.mufrizal.baseline.controller;

import org.rizki.mufrizal.baseline.mapper.harmonized.GeneralHarmonizedResponse;
import org.rizki.mufrizal.baseline.service.EndPointService;
import org.rizki.mufrizal.baseline.service.HarmonizedService;
import org.rizki.mufrizal.baseline.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/utilities")
public class CacheController {

    @Autowired
    private EndPointService endPointService;

    @Autowired
    private HarmonizedService harmonizedService;

    @Autowired
    private SystemParameterService systemParameterService;

    @GetMapping(value = "/cache/all")
    public ResponseEntity<?> clearAllCache() {
        endPointService.reload();
        harmonizedService.reload();
        systemParameterService.reload();

        GeneralHarmonizedResponse generalHarmonizedResponse = GeneralHarmonizedResponse.builder()
                .code("200")
                .description("Success")
                .build();

        return new ResponseEntity<>(generalHarmonizedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/cache/endpoint")
    public ResponseEntity<?> clearEndPointCache() {
        endPointService.reload();

        GeneralHarmonizedResponse generalHarmonizedResponse = GeneralHarmonizedResponse.builder()
                .code("200")
                .description("Success")
                .build();

        return new ResponseEntity<>(generalHarmonizedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/cache/harmonized")
    public ResponseEntity<?> clearHarmonizedCache() {
        harmonizedService.reload();

        GeneralHarmonizedResponse generalHarmonizedResponse = GeneralHarmonizedResponse.builder()
                .code("200")
                .description("Success")
                .build();

        return new ResponseEntity<>(generalHarmonizedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/cache/systemparameter")
    public ResponseEntity<?> clearSystemParameterCache() {
        systemParameterService.reload();

        GeneralHarmonizedResponse generalHarmonizedResponse = GeneralHarmonizedResponse.builder()
                .code("200")
                .description("Success")
                .build();

        return new ResponseEntity<>(generalHarmonizedResponse, HttpStatus.OK);
    }

}