package tn.esprim.gestionfoyer.service;

import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {

    public List<Etudiant> retrieveAllEtudiants();
    public Etudiant retrieveEtudiant(Long studentId);
    public Etudiant addEtudiant(Etudiant student);
    public void removeEtudiant(Long studentId);
    public Etudiant modifyEtudiant(Etudiant student);
}
