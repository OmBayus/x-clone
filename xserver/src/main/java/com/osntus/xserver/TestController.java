package com.osntus.xserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
//    @Value(value = "${test}")
//    private String test;
    @GetMapping("/")
    public ResponseEntity<String> testing(){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
