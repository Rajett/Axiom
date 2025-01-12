package com.dist.interview.axiomchat.axiomchatwsrest;

import com.dist.interview.axiomchat.axiomchatdal.entity.MessageEntity;
import com.dist.interview.axiomchat.axiomchatserviceapi.ChatModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/messages")
public class ChatWS {
    @Autowired
    private ChatModule messageService;


    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestParam String sender,
            @RequestParam String receiver,
            @RequestParam String content) {
        messageService.sendMessage(sender, receiver, content);
        return ResponseEntity.ok("Message envoyé avec succès !");
    }
    @GetMapping
    public ResponseEntity<List<MessageEntity>> getMessages(
            @RequestParam String sender,
            @RequestParam String receiver) {
        List<MessageEntity> messages = messageService.getMessages(sender, receiver);
        return ResponseEntity.ok(messages);
    }
}
