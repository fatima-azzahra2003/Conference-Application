package org.sid.web;


import com.example.keynoteservice.dto.KeynoteDTO;
import com.example.keynoteservice.entities.Keynote;
import com.example.keynoteservice.mapper.KeynoteMapper;
import com.example.keynoteservice.service.KeynoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/keynotes")
public class KeynoteController {
    private final KeynoteService service;
    public KeynoteController(KeynoteService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<KeynoteDTO> create(@RequestBody KeynoteDTO dto) {
        Keynote entity = KeynoteMapper.toEntity(dto);
        Keynote saved = service.save(entity);
        return ResponseEntity.created(URI.create("/api/keynotes/" + saved.getId()))
                .body(KeynoteMapper.toDto(saved));
    }

    @GetMapping
    public List<KeynoteDTO> all() {
        return service.findAll().stream().map(KeynoteMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<KeynoteDTO> get(@PathVariable UUID id) {
        return service.findById(id)
                .map(k -> ResponseEntity.ok(KeynoteMapper.toDto(k)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}