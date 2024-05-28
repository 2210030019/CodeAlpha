package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Room;

public class RoomService {
    private List<Room> rooms;

    public RoomService() {
        rooms = new ArrayList<>();
        // Populate the rooms with some initial data
        addRoom(1, "Single");
        addRoom(2, "Double");
        addRoom(3, "Suite");
    }

    public void addRoom(int roomId, String category) {
        Room room = new Room(roomId, category, "available");
        rooms.add(room);
    }

    public List<Room> getAvailableRoomsByCategory(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.getAvailability().equals("available")) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public List<Room> getAvailableRoomsByCategoryAndDates(String category, Date checkInDate, Date checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.getAvailability().equals("available")) {
                // Add logic to check room availability for the specified dates
                // For simplicity, assuming all rooms are available for any dates
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    public boolean bookRoom(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId && room.getAvailability().equals("available")) {
                room.setAvailability("booked");
                return true;
            }
        }
        return false;
    }

    public void freeRoom(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId && room.getAvailability().equals("booked")) {
                room.setAvailability("available");
            }
        }
    }
}