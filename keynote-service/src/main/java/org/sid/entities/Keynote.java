package org.sid.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keynote {
    @Id
    @GeneratedValue
    private UUID id;

    private String nom;
    private String prenom;
    private String email;
    private String fonction;
}