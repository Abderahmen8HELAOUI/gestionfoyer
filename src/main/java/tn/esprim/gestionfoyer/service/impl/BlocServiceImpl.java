package tn.esprim.gestionfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Bloc;
import tn.esprim.gestionfoyer.entities.Foyer;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.BlocRepository;
import tn.esprim.gestionfoyer.repositories.FoyerRepository;
import tn.esprim.gestionfoyer.service.IBlocService;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;
    private final FoyerRepository foyerRepository;

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId)
                .orElseThrow(() -> new ResourceNotFoundException("Bloc introuvable avec l'id : " + blocId));
    }

    @Override
    public Bloc addBloc(Bloc b) {

        if (b.getFoyer() == null || b.getFoyer().getIdFoyer() == null) {
            throw new RuntimeException("L'ID du foyer est obligatoire");
        }
        Long idFoyer = b.getFoyer().getIdFoyer();

        // 2. On va chercher le "vrai" Foyer complet dans la base de données
        Foyer vraiFoyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer introuvable avec l'id : " + idFoyer));

        // 3. On remplace le faux objet par le vrai avant de sauvegarder
        b.setFoyer(vraiFoyer);

        return blocRepository.save(b);
    }

    @Override
    public void removeBloc(Long blocId) {
        if (!blocRepository.existsById(blocId)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Bloc introuvable avec l'id : " + blocId);
        }
        blocRepository.deleteById(blocId);
    }

    @Override
    public Bloc modifyBloc(Bloc bloc) {
        if (bloc.getIdBloc() == null || !blocRepository.existsById(bloc.getIdBloc())) {
            throw new ResourceNotFoundException("Impossible de modifier : Bloc introuvable avec l'id : " + bloc.getIdBloc());
        }
        return blocRepository.save(bloc);
    }
}