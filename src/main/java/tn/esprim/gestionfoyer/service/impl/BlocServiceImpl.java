package tn.esprim.gestionfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Bloc;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.BlocRepository;
import tn.esprim.gestionfoyer.service.IBlocService;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;

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