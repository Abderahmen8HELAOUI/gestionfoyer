package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprim.gestionfoyer.entities.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer,Long> {
}
