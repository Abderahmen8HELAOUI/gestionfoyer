package tn.esprim.gestionfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Reservation;
import tn.esprim.gestionfoyer.exception.ResourceNotFoundException;
import tn.esprim.gestionfoyer.repositories.ReservationRepository;
import tn.esprim.gestionfoyer.service.IReservationService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(UUID reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable avec l'id : " + reservationId));
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
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
}