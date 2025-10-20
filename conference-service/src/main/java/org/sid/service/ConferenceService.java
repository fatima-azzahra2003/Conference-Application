package org.sid.service;


import com.example.conferenceservice.Repository.ConferenceRepository;
import com.example.conferenceservice.entities.Conference;
import com.example.conferenceservice.entities.Review;
import com.example.conferenceservice.feign.KeynoteClient;
import com.example.conferenceservice.feign.dto.KeynoteDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ConferenceService {

    private final ConferenceRepository repo;
    private final KeynoteClient keynoteClient;

    public ConferenceService(ConferenceRepository repo, KeynoteClient keynoteClient) {
        this.repo = repo;
        this.keynoteClient = keynoteClient;
    }

    public Conference save(Conference c) {
        return repo.save(c);
    }

    public List<Conference> findAll() {
        return repo.findAll();
    }

    public Conference findById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public Review addReview(UUID conferenceId, Review review) {
        Conference c = repo.findById(conferenceId).orElseThrow(() -> new IllegalArgumentException("Conference not found"));
        c.getReviews().add(review);
        Conference saved = repo.save(c);
        return saved.getReviews().get(saved.getReviews().size() - 1);
    }

    @CircuitBreaker(name = "keynoteClient", fallbackMethod = "keynoteFallback")
    public KeynoteDTO getKeynoteInfo(UUID keynoteId) {
        return keynoteClient.getKeynote(keynoteId);
    }

    public KeynoteDTO keynoteFallback(UUID keynoteId, Throwable t) {
        KeynoteDTO dto = new KeynoteDTO();
        dto.id = keynoteId;
        dto.nom = "inconnu";
        dto.prenom = "";
        dto.email = "";
        dto.fonction = "";
        return dto;
    }
}