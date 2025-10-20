package org.sid.mapper;


import com.example.keynoteservice.dto.KeynoteDTO;
import com.example.keynoteservice.entities.Keynote;

public class KeynoteMapper {
    public static KeynoteDTO toDto(Keynote k) {
        if (k == null) return null;
        KeynoteDTO d = new KeynoteDTO();
        d.id = k.getId();
        d.nom = k.getNom();
        d.prenom = k.getPrenom();
        d.email = k.getEmail();
        d.fonction = k.getFonction();
        return d;
    }
    public static Keynote toEntity(KeynoteDTO d) {
        if (d == null) return null;
        Keynote k = new Keynote();
        k.setId(d.id);
        k.setNom(d.nom);
        k.setPrenom(d.prenom);
        k.setEmail(d.email);
        k.setFonction(d.fonction);
        return k;
    }
}