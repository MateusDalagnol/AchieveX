package com.achievex.backend.shared.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @RequestMapping("/check")
    public ResponseEntity<String> retornaJson() {
        return new ResponseEntity<>("{\"status\": \"ok\"}",HttpStatus.OK);
    }

}
