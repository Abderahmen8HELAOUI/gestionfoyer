package tn.esprim.gestionfoyer.entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"foyer"}) // <-- Exclu pour éviter le StackOverflow
public class Universite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversite;

    private String nomUniversite;
    private String adresse;

    // Inverse side de l'association 1-1
    @OneToOne(mappedBy = "universite")
    @JsonIgnoreProperties("universite") // <-- Coupe la boucle JSON
    private Foyer foyer;

    @JsonCreator
    public Universite(@JsonProperty("idUniversite") Long idUniversite) {
        this.idUniversite = idUniversite;
    }
}
