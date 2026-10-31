package tn.esprim.gestionfoyer.service;

import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.entities.Reservation;

import java.util.List;
import java.util.UUID;

public interface IReservationService {

    public List<Reservation> retrieveAllReservations();
    public Reservation retrieveReservation(UUID reservationId);
    public Reservation addReservation(Reservation reservation);
    public void removeReservation(UUID reservationId);
    public Reservation modifyReservation(Reservation reservation);
    public  Reservation assignerEtudiant(UUID idReservation, Long idEtudiant);
}
