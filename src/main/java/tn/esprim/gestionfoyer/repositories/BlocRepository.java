package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprim.gestionfoyer.entities.Bloc;

public interface BlocRepository extends JpaRepository<Bloc,Long> {
}
