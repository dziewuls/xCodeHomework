package com.xcode.homeworkback.controller;

import com.xcode.homeworkback.domain.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class StatusController {

    private StatusService statusService;

    @GetMapping("/ping")
    public ResponseEntity getStatus() {
        String result = this.statusService.getStatus();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
