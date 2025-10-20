package org.sid.web;

import com.example.conferenceservice.DTO.ConferenceDTO;
import com.example.conferenceservice.DTO.ReviewDTO;
import com.example.conferenceservice.entities.Conference;
import com.example.conferenceservice.entities.Review;
import com.example.conferenceservice.mapper.ConferenceMapper;
import com.example.conferenceservice.service.ConferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    private final ConferenceService service;

    public ConferenceController(ConferenceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConferenceDTO> create(@RequestBody ConferenceDTO dto) {
        Conference entity = ConferenceMapper.toEntity(dto);
        Conference saved = service.save(entity);
        return ResponseEntity.created(URI.create("/api/conferences/" + saved.getId()))
                .body(ConferenceMapper.toDto(saved));
    }

    @GetMapping
    public List<ConferenceDTO> all() {
        return service.findAll().stream().map(ConferenceMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDTO> get(@PathVariable UUID id) {
        Conference c = service.findById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ConferenceMapper.toDto(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable UUID id, @RequestBody ReviewDTO reviewDto) {
        Review review = ConferenceMapper.toEntity(reviewDto);
        Review saved = service.addReview(id, review);
        return ResponseEntity.created(URI.create("/api/conferences/" + id + "/reviews/" + saved.getId()))
                .body(ConferenceMapper.toDto(saved));
    }

    @GetMapping("/{id}/keynote")
    public ResponseEntity<Object> getKeynoteInfo(@PathVariable UUID id, @RequestParam("keynoteId") UUID keynoteId) {
        Object keynote = service.getKeynoteInfo(keynoteId);
        return ResponseEntity.ok(keynote);
    }
}