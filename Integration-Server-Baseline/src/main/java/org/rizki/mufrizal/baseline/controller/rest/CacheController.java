package org.rizki.mufrizal.baseline.controller.rest;

import org.rizki.mufrizal.baseline.service.EndPointService;
import org.rizki.mufrizal.baseline.service.HarmonizedService;
import org.rizki.mufrizal.baseline.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("code", "200");
        stringObjectMap.put("description", "Success");

        return new ResponseEntity<>(stringObjectMap, HttpStatus.OK);
    }

    @GetMapping(value = "/cache/endpoint")
    public ResponseEntity<?> clearEndPointCache() {
        endPointService.reload();

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("code", "200");
        stringObjectMap.put("description", "Success");

        return new ResponseEntity<>(stringObjectMap, HttpStatus.OK);
    }

    @GetMapping(value = "/cache/harmonized")
    public ResponseEntity<?> clearHarmonizedCache() {
        harmonizedService.reload();

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("code", "200");
        stringObjectMap.put("description", "Success");

        return new ResponseEntity<>(stringObjectMap, HttpStatus.OK);
    }

    @GetMapping(value = "/cache/systemparameter")
    public ResponseEntity<?> clearSystemParameterCache() {
        systemParameterService.reload();

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("code", "200");
        stringObjectMap.put("description", "Success");

        return new ResponseEntity<>(stringObjectMap, HttpStatus.OK);
    }

}