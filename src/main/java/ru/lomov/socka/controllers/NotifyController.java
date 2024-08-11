package ru.lomov.socka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lomov.socka.entities.Notify;
import ru.lomov.socka.services.NotifyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifies")
public class NotifyController {


    private final NotifyService notifyService;

    @PostMapping
    public ResponseEntity<Notify> createNotify(@RequestBody Notify notify) {
        return ResponseEntity.ok(notifyService.createNotify(notify));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notify> removeNotify(@PathVariable Long id, @RequestParam String url) {
        return ResponseEntity.ok(notifyService.removeNotify(id, url));
    }

    @GetMapping
    public ResponseEntity<List<Notify>> getNotifies(@RequestParam Long userId) {
        return ResponseEntity.ok(notifyService.getNotifies(userId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Notify> isReadNotify(@PathVariable Long id) {
        return ResponseEntity.ok(notifyService.isReadNotify(id));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllNotifies(@RequestParam Long userId) {
        notifyService.deleteAllNotifies(userId);
        return ResponseEntity.ok().build();
    }
}
