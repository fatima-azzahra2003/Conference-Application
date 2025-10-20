package org.sid.service;

import org.sid.entities.Keynote;
import org.sid.repository.KeynoteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KeynoteService {
    private final KeynoteRepository repo;

    public KeynoteService(KeynoteRepository repo) {
        this.repo = repo;
    }

    public Keynote save(Keynote k) { return repo.save(k); }
    public List<Keynote> findAll() { return repo.findAll(); }
    public Optional<Keynote> findById(UUID id) { return repo.findById(id); }
    public void delete(UUID id) { repo.deleteById(id); }
}