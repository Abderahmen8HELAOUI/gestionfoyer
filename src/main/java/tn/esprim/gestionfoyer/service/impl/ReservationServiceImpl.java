package tn.esprim.gestionfoyer.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprim.gestionfoyer.entities.Etudiant;
import tn.esprim.gestionfoyer.entities.Reservation;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.EtudiantRepository;
import tn.esprim.gestionfoyer.repositories.ReservationRepository;
import tn.esprim.gestionfoyer.service.IReservationService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final EtudiantRepository etudiantRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(UUID reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable avec l'id : " + reservationId));
    }

//    @Override
//    public Reservation addReservation(Reservation r) {
//        return reservationRepository.save(r);
//    }

    @PersistenceContext
    private EntityManager entityManager; // <-- Injectez ceci

    @Override
    @Transactional
    public Reservation addReservation(Reservation r) {
        // 1. Sauvegarder
        Reservation saved = reservationRepository.saveAndFlush(r);

        // 2. Vider le cache de session pour oublier l'objet "vide" envoyé par Postman
        entityManager.clear();

        // 3. Maintenant, findById va VRAIMENT faire un SELECT en base
        // et grâce à votre FetchType.EAGER, il va charger le Bloc !
        return reservationRepository.findById(saved.getIdReservation())
                .orElseThrow(() -> new RuntimeException("Erreur"));
    }

    @Override
    public void removeReservation(UUID reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Réservation introuvable avec l'id : " + reservationId);
        }
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) {
        if (reservation.getIdReservation() == null || !reservationRepository.existsById(reservation.getIdReservation())) {
            throw new ResourceNotFoundException("Impossible de modifier : Réservation introuvable avec l'id : " + reservation.getIdReservation());
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation assignerEtudiant(UUID idReservation, Long idEtudiant) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));

        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));

        etudiant.getReservations().add(reservation);
        reservation.getEtudiants().add(etudiant);

        etudiantRepository.save(etudiant);

        return reservationRepository.save(reservation);
    }
}