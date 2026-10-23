package tn.esprim.gestionfoyer.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// On exclut les listes du ToString pour éviter le StackOverflowError
@ToString(exclude = {"chambres", "foyer"})
public class Bloc implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idBloc;

   private String nomBloc;
   private Long capaciteBloc;

   // Relation *-1 avec Foyer
   @ManyToOne
   @JoinColumn(name = "id_foyer")
   @JsonIgnoreProperties("blocs") // <-- À ajouter (suppose que Foyer a un Set<Bloc> blocs)
   private Foyer foyer;

   // Relation 1-* avec Chambre
   @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL)
   @JsonIgnoreProperties("bloc") // <-- À ajouter pour couper la boucle JSON
   private Set<Chambre> chambres;
}