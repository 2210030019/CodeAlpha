package controller;
import java.util.Date;
import java.util.List;
import models.Reservation;
import models.Room;
import models.User;
import service.PaymentService;
import service.ReservationService;
import service.RoomService;
import service.UserService;

public class HotelReservationController {
    private RoomService roomService;
    private ReservationService reservationService;
    private UserService userService;
    private PaymentService paymentService;

    public HotelReservationController(RoomService roomService, ReservationService reservationService, UserService userService, PaymentService paymentService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public List<Room> searchAvailableRooms(String category, Date checkInDate, Date checkOutDate) {
        return roomService.getAvailableRoomsByCategoryAndDates(category, checkInDate, checkOutDate);
    }

    public boolean makeReservation(int roomId, int userId, Date checkInDate, Date checkOutDate, double amount, String cardNumber, String expiryDate, String cvv) {
        Room room = roomService.getRoomById(roomId);
        User user = userService.getUserById(userId);

        if (room != null && user != null) {
            if (paymentService.processPayment(amount, cardNumber, expiryDate, cvv)) {
                boolean reservationCreated = reservationService.createReservation(roomId, userId, checkInDate, checkOutDate);
                if (reservationCreated) {
                    boolean roomBooked = roomService.bookRoom(roomId);
                    return roomBooked;
                }
            }
        }
        return false;
    }

    public List<Reservation> getReservationsByUser(int userId) {
        return reservationService.getReservationsByUser(userId);
    }

    public boolean cancelReservation(int reservationId) {
        boolean reservationCancelled = reservationService.cancelReservation(reservationId);
        if (reservationCancelled) {
            Reservation reservation = getReservationById(reservationId);
            roomService.freeRoom(reservation.getRoomId());
            return true;
        }
        return false;
    }

    private Reservation getReservationById(int reservationId) {
        List<Reservation> allReservations = reservationService.getReservationsByUser(0); // Get all reservations
        for (Reservation reservation : allReservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
}