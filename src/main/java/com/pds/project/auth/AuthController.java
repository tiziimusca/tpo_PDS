package com.pds.project.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/rol/{rol}")
    public ResponseEntity<String> obtenerToken(@PathVariable Rol rol) {
        String token = Base64.getEncoder().encodeToString(rol.name().getBytes());
        return ResponseEntity.ok(token);
    }
}
