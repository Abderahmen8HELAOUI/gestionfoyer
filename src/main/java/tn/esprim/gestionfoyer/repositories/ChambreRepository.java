package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprim.gestionfoyer.entities.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre,Long> {
}
