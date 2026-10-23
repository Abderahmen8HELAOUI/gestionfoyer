package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprim.gestionfoyer.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
