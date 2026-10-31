package tn.esprim.gestionfoyer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID idReservation;

    // Approche moins idéale pour une période
    @JsonFormat(pattern = "yyyy-MM-dd")
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
