package library360.autorms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {
    @GetMapping("")
    public ResponseEntity<String> getHealth(){
        return ResponseEntity.ok().body("'Autor' Microservice online!");
    }

}
