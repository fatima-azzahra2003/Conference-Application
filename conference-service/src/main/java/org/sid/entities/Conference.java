package org.sid.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {

    @Id
    @GeneratedValue
    private UUID id;

    private String titre;
    private String type;
    private LocalDate date;
    private Integer duree;
    private Integer nbInscrits;
    private Double score;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conference_id")
    private List<Review> reviews = new ArrayList<>();

    public Conference(UUID id, String titre, String type, LocalDate date,
                      Integer duree, Integer nbInscrits, Double score) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.date = date;
        this.duree = duree;
        this.nbInscrits = nbInscrits;
        this.score = score;
    }


}