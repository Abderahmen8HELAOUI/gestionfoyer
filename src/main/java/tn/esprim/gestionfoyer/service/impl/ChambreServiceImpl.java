package tn.esprim.gestionfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Bloc;
import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.BlocRepository;
import tn.esprim.gestionfoyer.repositories.ChambreRepository;
import tn.esprim.gestionfoyer.service.IChambreService;

import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;
    private final BlocRepository blocRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId)
                .orElseThrow(() -> new ResourceNotFoundException("Chambre introuvable avec l'id : " + chambreId));
    }

    @Override
    public Chambre addChambre(Chambre c) {

        if (c.getBloc() == null || c.getBloc().getIdBloc() == null) {
            throw new RuntimeException("L'ID du bloc est obligatoire");
        }
        Long idBloc = c.getBloc().getIdBloc();

        // 2. Récupérer le vrai Bloc depuis la base de données
        Bloc vraiBloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable avec l'id : " + idBloc));

        // 3. Associer le vrai Bloc à la Chambre
        c.setBloc(vraiBloc);

        return chambreRepository.save(c);
    }

    @Override
    public void removeChambre(Long chambreId) {
        if (!chambreRepository.existsById(chambreId)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Chambre introuvable avec l'id : " + chambreId);
        }
        chambreRepository.deleteById(chambreId);
    }

    @Override
    public Chambre modifyChambre(Chambre chambre) {
        if (chambre.getIdChambre() == null || !chambreRepository.existsById(chambre.getIdChambre())) {
            throw new ResourceNotFoundException("Impossible de modifier : Chambre introuvable avec l'id : " + chambre.getIdChambre());
        }
        return chambreRepository.save(chambre);
    }
}