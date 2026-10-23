package tn.esprim.gestionfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"bloc", "reservations"})
public class Chambre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;

    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    // Relation *-1 avec Bloc
    // Dans l'entité Chambre
    @ManyToOne
    @JoinColumn(name = "id_bloc")
    @JsonIgnoreProperties("chambres") // <-- Coupe la boucle côté Chambre
    private Bloc bloc;

    // Relation 1-* avec Reservation
    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("chambres")
    private Set<Reservation> reservations;
}
