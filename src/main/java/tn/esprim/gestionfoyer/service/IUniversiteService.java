package tn.esprim.gestionfoyer.service;

import tn.esprim.gestionfoyer.entities.Universite;

import java.util.List;

public interface IUniversiteService {

    public List<Universite> retrieveAllUniversites();
    public Universite retrieveUniversite(Long universiteId);
    public Universite addUniversite(Universite universite);
    public void removeUniversite(Long universiteId);
    public Universite modifyUniversite(Universite universite);
}
