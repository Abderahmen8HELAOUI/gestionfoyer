package tn.esprim.gestionfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"universite", "blocs"})
public class Foyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;

    private String nomFoyer;
    private Long capaciteFoyer;

    // Foyer est le fils (détient la FK id_universite)
    @OneToOne
    @JoinColumn(name = "id_universite")
    @JsonIgnoreProperties("foyer")
    private Universite universite;

    // Relation 1-* avec Bloc
    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("foyer")
    private Set<Bloc> blocs;
}