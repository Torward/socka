package ru.lomov.socka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lomov.socka.entities.Conversation;
import ru.lomov.socka.entities.Message;
import ru.lomov.socka.repositories.ConversationRepository;
import ru.lomov.socka.repositories.MessageRepository;

import java.util.List;
@RequiredArgsConstructor
@Service
public class MessageService {


    private final ConversationRepository conversationRepository;


    private final MessageRepository messageRepository;

    public void createMessage(Message message) {
        Conversation conversation = conversationRepository.findById(message.getConversation()).orElseThrow(() -> new RuntimeException("Conversation not found"));
        messageRepository.save(message);
    }

    public List<Conversation> getConversations(Long userId) {
        return conversationRepository.findAllByRecipientsContaining(userId);
    }

    public List<Message> getMessages(Long userId, Long recipientId) {
        return messageRepository.findAllBySenderAndRecipient(userId, recipientId);
    }
}