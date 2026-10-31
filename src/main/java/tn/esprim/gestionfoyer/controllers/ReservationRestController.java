package tn.esprim.gestionfoyer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprim.gestionfoyer.entities.Reservation;
import tn.esprim.gestionfoyer.service.IReservationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationRestController {

    private final IReservationService reservationService;

    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservations();
    }

    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") UUID resId) { // <-- UUID
        return reservationService.retrieveReservation(resId);
    }

    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        return reservationService.addReservation(r);
    }

    @PostMapping("/{idReservation}/etudiant/{idEtudiant}")
    public ResponseEntity<Reservation> assignerEtudiant(
            @PathVariable("idReservation") UUID idReservation, // <-- Changé en UUID
            @PathVariable("idEtudiant") Long idEtudiant) {

        try {
            Reservation reservationMiseAJour = reservationService.assignerEtudiant(idReservation, idEtudiant);
            return ResponseEntity.ok(reservationMiseAJour);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") UUID resId) { // <-- UUID
        reservationService.removeReservation(resId);
    }

    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        return reservationService.modifyReservation(r);
    }
}
