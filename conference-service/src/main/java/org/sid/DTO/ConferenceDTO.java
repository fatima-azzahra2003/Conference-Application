package org.sid.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConferenceDTO {
    public UUID id;
    public String titre;
    public String type;
    public LocalDate date;
    public Integer duree;
    public Integer nbInscrits;
    public Double score;
    public List<ReviewDTO> reviews = new ArrayList<>();
}