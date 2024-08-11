package ru.lomov.socka.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lomov.socka.entities.Conversation;
import ru.lomov.socka.entities.Message;
import ru.lomov.socka.services.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
@Tag(name = "MessageController", description = "Обеспечивает работу чата")
public class MessageController {


    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "Post message", description = "Добавляет сообщение в базу данных, возвращает пустой ResponseEntity")
    @ApiResponse(responseCode = "200", description = "Сообщение добавлено")
    public ResponseEntity<Void> createMessage(@RequestBody Message message) {
        messageService.createMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> getConversations(@RequestParam Long userId) {
        return ResponseEntity.ok(messageService.getConversations(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Message>> getMessages(@RequestParam Long userId, @PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessages(userId, id));
    }
}