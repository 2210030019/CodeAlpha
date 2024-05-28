package models;
//Room.java
public class Room {
 private int roomId;
 private String category;
 private String availability;

 public Room(int roomId, String category, String availability) {
     this.roomId = roomId;
     this.category = category;
     this.availability = availability;
 }

 // Getters and Setters
 public int getRoomId() {
     return roomId;
 }

 public void setRoomId(int roomId) {
     this.roomId = roomId;
 }

 public String getCategory() {
     return category;
 }

 public void setCategory(String category) {
     this.category = category;
 }

 public String getAvailability() {
     return availability;
 }

 public void setAvailability(String availability) {
     this.availability = availability;
 }
}
