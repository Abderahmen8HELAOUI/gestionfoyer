package tn.esprim.gestionfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Etudiant;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.EtudiantRepository;
import tn.esprim.gestionfoyer.service.IEtudiantService;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant retrieveEtudiant(Long etudiantId) {
        return etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new ResourceNotFoundException("Etudiant introuvable avec l'id : " + etudiantId));
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public void removeEtudiant(Long etudiantId) {
        if (!etudiantRepository.existsById(etudiantId)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Etudiant introuvable avec l'id : " + etudiantId);
        }
        etudiantRepository.deleteById(etudiantId);
    }

    @Override
    public Etudiant modifyEtudiant(Etudiant etudiant) {
        if (etudiant.getIdEtudiant() == null || !etudiantRepository.existsById(etudiant.getIdEtudiant())) {
            throw new ResourceNotFoundException("Impossible de modifier : Etudiant introuvable avec l'id : " + etudiant.getIdEtudiant());
        }
        return etudiantRepository.save(etudiant);
    }
}