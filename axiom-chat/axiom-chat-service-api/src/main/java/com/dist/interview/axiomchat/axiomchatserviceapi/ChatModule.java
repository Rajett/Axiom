package com.dist.interview.axiomchat.axiomchatserviceapi;

import com.dist.interview.axiomchat.axiomchatdal.entity.MessageEntity;

import java.util.*;

public interface ChatModule {
    List<MessageEntity> getMessages(String sender, String receiver);

    void sendMessage(String sender, String receiver, String content);
}
