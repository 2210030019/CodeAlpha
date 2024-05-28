package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Reservation;
public class ReservationService {
private List<Reservation> reservations;

public ReservationService() {
   reservations = new ArrayList<>();
}

public boolean createReservation(int roomId, int userId, Date checkInDate, Date checkOutDate) {
   Reservation reservation = new Reservation(reservations.size() + 1, roomId, userId, checkInDate, checkOutDate);
   reservations.add(reservation);
   return true;
}

public List<Reservation> getReservationsByUser(int userId) {
   List<Reservation> userReservations = new ArrayList<>();
   for (Reservation reservation : reservations) {
       if (reservation.getUserId() == userId) {
           userReservations.add(reservation);
       }
   }
   return userReservations;
}

public boolean cancelReservation(int reservationId) {
   for (Reservation reservation : reservations) {
       if (reservation.getReservationId() == reservationId) {
           reservations.remove(reservation);
           return true;
       }
   }
   return false;
}
}