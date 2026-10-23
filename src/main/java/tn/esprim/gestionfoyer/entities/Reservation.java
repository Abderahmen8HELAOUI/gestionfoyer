package tn.esprim.gestionfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"chambre", "etudiants"})
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // <-- Génération auto
    private UUID idReservation;

    @Temporal(TemporalType.DATE)
    private Date anneeUniversitaire;

    private boolean estValide;

    @ManyToOne
    @JoinColumn(name = "id_chambre")
    @JsonIgnoreProperties("reservations")
    private Chambre chambre;

    @ManyToMany(mappedBy = "reservations")
    @JsonIgnoreProperties("reservations")
    private Set<Etudiant> etudiants;
}
