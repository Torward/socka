package ru.lomov.socka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lomov.socka.entities.Notify;
import ru.lomov.socka.repositories.NotifyRepository;

import java.util.List;
@RequiredArgsConstructor
@Service
public class NotifyService {


    private final NotifyRepository notifyRepository;

    public Notify createNotify(Notify notify) {
        return notifyRepository.save(notify);
    }

    public Notify removeNotify(Long id, String url) {
        Notify notify = notifyRepository.findById(id).orElseThrow(() -> new RuntimeException("Notify not found"));
        if (notify.getUrl().equals(url)) {
            notifyRepository.delete(notify);
        }
        return notify;
    }

    public List<Notify> getNotifies(Long userId) {
        return notifyRepository.findAllByRecipientsContaining(userId);
    }

    public Notify isReadNotify(Long id) {
        Notify notify = notifyRepository.findById(id).orElseThrow(() -> new RuntimeException("Notify not found"));
        notify.setIsRead(true);
        return notifyRepository.save(notify);
    }

    public void deleteAllNotifies(Long userId) {
        notifyRepository.deleteAllByRecipientsContaining(userId);
    }
}