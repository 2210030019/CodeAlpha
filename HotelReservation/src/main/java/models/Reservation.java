package models;
import java.util.Date;

public class Reservation {
private int reservationId;
private int roomId;
private int userId;
private Date checkInDate;
private Date checkOutDate;

public Reservation(int reservationId, int roomId, int userId, Date checkInDate, Date checkOutDate) {
   this.reservationId = reservationId;
   this.roomId = roomId;
   this.userId = userId;
   this.checkInDate = checkInDate;
   this.checkOutDate = checkOutDate;
}

// Getters and Setters
public int getReservationId() {
   return reservationId;
}

public void setReservationId(int reservationId) {
   this.reservationId = reservationId;
}

public int getRoomId() {
   return roomId;
}

public void setRoomId(int roomId) {
   this.roomId = roomId;
}

public int getUserId() {
   return userId;
}

public void setUserId(int userId) {
   this.userId = userId;
}

public Date getCheckInDate() {
   return checkInDate;
}

public void setCheckInDate(Date checkInDate) {
   this.checkInDate = checkInDate;
}

public Date getCheckOutDate() {
   return checkOutDate;
}

public void setCheckOutDate(Date checkOutDate) {
   this.checkOutDate = checkOutDate;
}
}