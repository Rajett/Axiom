package com.dist.interview.axiomchat.axiomchatdal.repo;

import com.dist.interview.axiomchat.axiomchatdal.entity.MessageEntity;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;




@Repository
public class  AxiomRepository {

    private final String axiomUrl = "https://api.axiom.co/v1/datasets/{interviewapp}/ingest";
    private final String apiKey = "Bearer xapt-901790d1-0a77-4834-afb2-94ce56d2ea17";
    public List<MessageEntity> fetchMessages(String sender, String receiver) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);

        String url = axiomUrl + "?sender=" + sender + "&receiver=" + receiver;
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<MessageEntity[]> response = restTemplate.exchange(
                url, HttpMethod.GET, request, MessageEntity[].class);

        return response.getBody() != null ? Arrays.asList(response.getBody()) : new ArrayList<>();
    }

    public void saveMessage(MessageEntity message) {
        RestTemplate restTemplate = new RestTemplate();

        // Créer les headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Convertir le message en JSON
        HttpEntity<MessageEntity> request = new HttpEntity<>(message, headers);

        // Envoyer la requête POST
        restTemplate.postForEntity(axiomUrl, request, String.class);
    }
}
