package com.dist.interview.axiomauth.serviceimpl;

import com.dist.interview.axiomauth.daljpa.User;
import com.dist.interview.axiomauth.serviceapi.AxiomModule;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
public class AxiomService implements AxiomModule {
    private final RestTemplate restTemplate;

    public AxiomService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public void saveUserToAxiom(String email, String password) {
        String url = "https://api.axiom.co/v1/datasets/interviewapp/ingest";

        // Data preparation
        User user = new User(email, password);
        Object[] users = { user };

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer xapt-901790d1-0a77-4834-afb2-94ce56d2ea17");
        headers.set("Content-Type", "application/json");
        headers.set("X-AXIOM-ORG-ID", "interviewapp-8qn7");

        HttpEntity<Object[]> requestEntity = new HttpEntity<>(users, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println("Response from Axiom: " + response.getBody());
        } catch (Exception ex) {
            throw new RuntimeException("Error sending data to Axiom: " + ex.getMessage(), ex);
        }
    }
}
