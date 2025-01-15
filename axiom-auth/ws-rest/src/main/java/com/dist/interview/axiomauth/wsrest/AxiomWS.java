package com.dist.interview.axiomauth.wsrest;

import com.dist.interview.axiomauth.daljpa.User;
import com.dist.interview.axiomauth.serviceapi.AxiomModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AxiomWS {
    @Autowired
    private AxiomModule axiomModule;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            axiomModule.saveUserToAxiom(user.getEmail(), user.getPassword());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error registering user", ex.getMessage()));
        }
    }

    public static class ErrorResponse {
        private final String message;
        private final String details;

        public ErrorResponse(String message, String details) {
            this.message = message;
            this.details = details;
        }

        public String getMessage() {
            return message;
        }

        public String getDetails() {
            return details;
        }
    }
}
