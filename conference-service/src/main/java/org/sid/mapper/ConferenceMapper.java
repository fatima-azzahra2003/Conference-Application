package org.sid.mapper;


import com.example.conferenceservice.DTO.ConferenceDTO;
import com.example.conferenceservice.DTO.ReviewDTO;
import com.example.conferenceservice.entities.Conference;
import com.example.conferenceservice.entities.Review;

import java.util.stream.Collectors;

public class ConferenceMapper {

    public static ConferenceDTO toDto(Conference c) {
        if (c == null) return null;
        ConferenceDTO d = new ConferenceDTO();
        d.id = c.getId();
        d.titre = c.getTitre();
        d.type = c.getType();
        d.date = c.getDate();
        d.duree = c.getDuree();
        d.nbInscrits = c.getNbInscrits();
        d.score = c.getScore();
        d.reviews = c.getReviews().stream().map(ConferenceMapper::toDto).collect(Collectors.toList());
        return d;
    }

    public static ReviewDTO toDto(Review r) {
        if (r == null) return null;
        ReviewDTO d = new ReviewDTO();
        d.id = r.getId();
        d.date = r.getDate();
        d.texte = r.getTexte();
        d.note = r.getNote();
        return d;
    }

    public static Conference toEntity(ConferenceDTO d) {
        if (d == null) return null;
        Conference c = new Conference();
        c.setId(d.id);
        c.setTitre(d.titre);
        c.setType(d.type);
        c.setDate(d.date);
        c.setDuree(d.duree);
        c.setNbInscrits(d.nbInscrits);
        c.setScore(d.score);
        if (d.reviews != null) {
            c.getReviews().clear();
            c.getReviews().addAll(d.reviews.stream().map(ConferenceMapper::toEntity).collect(Collectors.toList()));
        }
        return c;
    }

    public static Review toEntity(ReviewDTO d) {
        if (d == null) return null;
        Review r = new Review();
        r.setId(d.id);
        r.setDate(d.date);
        r.setTexte(d.texte);
        r.setNote(d.note);
        return r;
    }
}