package tn.esprim.gestionfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Universite;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.UniversiteRepository;
import tn.esprim.gestionfoyer.service.IUniversiteService;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite retrieveUniversite(Long universiteId) {
        return universiteRepository.findById(universiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Université introuvable avec l'id : " + universiteId));
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public void removeUniversite(Long universiteId) {
        if (!universiteRepository.existsById(universiteId)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Université introuvable avec l'id : " + universiteId);
        }
        universiteRepository.deleteById(universiteId);
    }

    @Override
    public Universite modifyUniversite(Universite universite) {
        if (universite.getIdUniversite() == null || !universiteRepository.existsById(universite.getIdUniversite())) {
            throw new ResourceNotFoundException("Impossible de modifier : Université introuvable avec l'id : " + universite.getIdUniversite());
        }
        return universiteRepository.save(universite);
    }
}