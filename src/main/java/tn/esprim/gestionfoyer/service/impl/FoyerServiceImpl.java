package tn.esprim.gestionfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Foyer;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.FoyerRepository;
import tn.esprim.gestionfoyer.service.IFoyerService;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    private final FoyerRepository foyerRepository;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer retrieveFoyer(Long foyerId) {
        return foyerRepository.findById(foyerId)
                .orElseThrow(() -> new ResourceNotFoundException("Foyer introuvable avec l'id : " + foyerId));
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public void removeFoyer(Long foyerId) {
        if (!foyerRepository.existsById(foyerId)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Foyer introuvable avec l'id : " + foyerId);
        }
        foyerRepository.deleteById(foyerId);
    }

    @Override
    public Foyer modifyFoyer(Foyer foyer) {
        if (foyer.getIdFoyer() == null || !foyerRepository.existsById(foyer.getIdFoyer())) {
            throw new ResourceNotFoundException("Impossible de modifier : Foyer introuvable avec l'id : " + foyer.getIdFoyer());
        }
        return foyerRepository.save(foyer);
    }
}