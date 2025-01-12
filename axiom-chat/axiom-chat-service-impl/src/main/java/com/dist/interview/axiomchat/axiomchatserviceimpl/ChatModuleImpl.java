package com.dist.interview.axiomchat.axiomchatserviceimpl;

import com.dist.interview.axiomchat.axiomchatdal.entity.MessageEntity;
import com.dist.interview.axiomchat.axiomchatdal.repo.AxiomRepository;
import com.dist.interview.axiomchat.axiomchatserviceapi.ChatModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatModuleImpl implements ChatModule {
    @Autowired
    private AxiomRepository axiomRepository;


    @Override
    public List<MessageEntity> getMessages(String sender, String receiver) {
        // Hypothèse : Une méthode `fetchMessages` existe dans AxiomRepository
        return axiomRepository.fetchMessages(sender, receiver);
    }

    @Override
    public void sendMessage(String sender, String receiver, String content) {
        MessageEntity message = new MessageEntity();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        // Sauvegarder le message dans Axiom
        axiomRepository.saveMessage(message);
    }
}
